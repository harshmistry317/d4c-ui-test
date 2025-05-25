package com.hkm.d4cshop.screens.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hkm.d4cshop.R
import com.hkm.d4cshop.composeutils.ItemCard
import com.hkm.d4cshop.screens.shop.ShopUiState
import com.hkm.d4cshop.screens.shop.component.banner.PromoBannerPager
import com.hkm.d4cshop.screens.shop.component.category.Category
import com.hkm.d4cshop.ui.theme.InStockGreen
import com.hkm.d4cshop.ui.theme.PrimaryText

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    when(state){
        is CartUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = InStockGreen
                )
            }
        }
        is CartUiState.Error -> {
            val error = state as CartUiState.Error
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = error.message ?: "Unknown error",style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryText))
            }
        }
        is CartUiState.Success ->{
            val data = state as CartUiState.Success
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = TweenSpec(250))
            ) {
                if (data.products.isEmpty()){
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "No item in cart",style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryText, fontSize = 24.sp))
                    }
                }else {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())) {
                        Spacer(Modifier.height(16.dp))
                        data.products.forEachIndexed { index, productData ->
                            if (index != 0) {
                                Spacer(Modifier.height(16.dp))
                            }
                            ItemCard(productData,onLikeClick = {
                                viewModel.updateProduct(it.copy(isLiked = !it.isLiked))
                            },onCartClick = {
                                viewModel.updateProduct(it.copy(isInCart = !it.isInCart))
                            })
                        }
                        Spacer(Modifier.height(16.dp))



                    }
                }

            }

        }
    }
}