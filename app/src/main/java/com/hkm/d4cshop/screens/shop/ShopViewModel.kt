package com.hkm.d4cshop.screens.shop

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hkm.d4cshop.data.local.BannerDao
import com.hkm.d4cshop.data.local.CategoryDao
import com.hkm.d4cshop.data.local.ProductDao
import com.hkm.d4cshop.models.BannerData
import com.hkm.d4cshop.models.CategoryData
import com.hkm.d4cshop.models.ProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class ShopUiState {
    object Loading : ShopUiState()
    data class Success(
        val banners: List<BannerData>,
        val categories: List<CategoryData>,
        val products: List<ProductData>
    ) : ShopUiState()

    data class Error(val message: String) : ShopUiState()
}

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val application: Application,
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val bannerDao: BannerDao
) : AndroidViewModel(application)  {




    private val _uiState = MutableStateFlow<ShopUiState>(ShopUiState.Loading)
    val uiState: StateFlow<ShopUiState> = _uiState


     val selectedCategory = MutableStateFlow<CategoryData?>(null)

    init {
        observeShopData()
    }

    private fun observeShopData() {
        viewModelScope.launch {
            try {
                with(Dispatchers.IO){
                    combine(
                        bannerDao.getAllBanner(),
                        categoryDao.getAllCategory(),
                        selectedCategory,
                        productDao.getAllProduct()
                    ) { banners, categories, selectedCategory,products ->
                        // Filter products if a category is selected, else show all
                        val filteredProducts = selectedCategory?.let { category ->
                            products.filter { it.categoryId == category.categoryId }
                        } ?: products

                        ShopUiState.Success(
                            banners = banners,
                            categories = categories,
                            products = filteredProducts
                        )
                    }.collect { state ->
                        _uiState.value = state
                    }
                }

            } catch (e: Exception) {
                _uiState.value = ShopUiState.Error("Failed to load data: ${e.localizedMessage}")
            }
        }
    }

    fun updateProduct(product: ProductData) {
        viewModelScope.launch {
            productDao.upsertProduct(product)
            // No need to reload manually – collect will auto-update
        }
    }

    fun onCateGoryClick(categoryData: CategoryData){
        viewModelScope.launch {
            if (selectedCategory.value == categoryData){
                selectedCategory.value = null
                val allProduct = productDao.getAllProduct()
                val currentSate = uiState.value as ShopUiState.Success
                _uiState.value = currentSate.copy(products = allProduct.first() )

            }else {
                selectedCategory.value = categoryData
                val productWithCategory = productDao.getProductsByCategoryId(categoryId = selectedCategory?.value?.categoryId ?: 0)
                val currentSate = uiState.value as ShopUiState.Success
                _uiState.value = currentSate.copy(products = productWithCategory.first() )
            }
        }

    }
}