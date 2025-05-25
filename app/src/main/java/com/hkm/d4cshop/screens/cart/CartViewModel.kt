package com.hkm.d4cshop.screens.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import com.hkm.d4cshop.data.local.BannerDao
import com.hkm.d4cshop.data.local.CategoryDao
import com.hkm.d4cshop.data.local.ProductDao
import com.hkm.d4cshop.models.BannerData
import com.hkm.d4cshop.models.CategoryData
import com.hkm.d4cshop.models.ProductData
import com.hkm.d4cshop.screens.shop.ShopUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class CartUiState {
    object Loading : CartUiState()
    data class Success(
        val products: List<ProductData>
    ) : CartUiState()

    data class Error(val message: String) : CartUiState()
}
@HiltViewModel
class CartViewModel @Inject constructor(
    private val application: Application,
    private val productDao: ProductDao,
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<CartUiState>(CartUiState.Loading)
    val uiState: StateFlow<CartUiState> = _uiState

    init {
        observeCartData()
    }

    fun observeCartData() {
        viewModelScope.launch {
            try {
                productDao.getProductsInCart().collect { products ->
                    _uiState.value = CartUiState.Success(products = products)
                }
            } catch (e: Exception) {
                _uiState.value = CartUiState.Error("Failed to load data: ${e.localizedMessage}")
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