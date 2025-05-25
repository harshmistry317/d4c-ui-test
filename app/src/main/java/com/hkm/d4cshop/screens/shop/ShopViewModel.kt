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
import kotlinx.coroutines.async
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

    init {
        observeShopData()
    }

    private fun observeShopData() {
        viewModelScope.launch {
            try {
                combine(
                    bannerDao.getAllBanner(),
                    categoryDao.getAllCategory(),
                    productDao.getAllProduct()
                ) { banners, categories, products ->
                    ShopUiState.Success(
                        banners = banners,
                        categories = categories,
                        products = products
                    )
                }.collect { state ->
                    _uiState.value = state
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
}