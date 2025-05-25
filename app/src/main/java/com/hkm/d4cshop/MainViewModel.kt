package com.hkm.d4cshop

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val bannerDao: BannerDao
) : AndroidViewModel(application) {
    val getCartProduct : LiveData<List<ProductData>> = productDao.getProductsInCart().asLiveData()
    val getLikedProduct : LiveData<List<ProductData>> = productDao.getProductsLiked().asLiveData()
}