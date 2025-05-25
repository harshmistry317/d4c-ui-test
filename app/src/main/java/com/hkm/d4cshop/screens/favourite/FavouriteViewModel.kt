package com.hkm.d4cshop.screens.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hkm.d4cshop.data.local.ProductDao
import com.hkm.d4cshop.models.ProductData
import com.hkm.d4cshop.screens.cart.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class FavouriteUiState {
    object Loading : FavouriteUiState()
    data class Success(
        val products: List<ProductData>
    ) : FavouriteUiState()

    data class Error(val message: String) : FavouriteUiState()
}

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val application: Application,
    private val productDao: ProductDao,
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<FavouriteUiState>(FavouriteUiState.Loading)
    val uiState: StateFlow<FavouriteUiState> = _uiState

    init {
        observeCartData()
    }

    fun observeCartData() {
        viewModelScope.launch {
            try {
                productDao.getProductsLiked().collect { products ->
                    _uiState.value = FavouriteUiState.Success(products = products)
                }
            } catch (e: Exception) {
                _uiState.value = FavouriteUiState.Error("Failed to load data: ${e.localizedMessage}")
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