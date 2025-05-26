package com.hkm.d4cshop.screens.shop

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hkm.d4cshop.R
import com.hkm.d4cshop.composeutils.ItemCard
import com.hkm.d4cshop.screens.shop.component.banner.PromoBannerPager
import com.hkm.d4cshop.models.BannerData
import com.hkm.d4cshop.models.CategoryData
import com.hkm.d4cshop.models.ProductData
import com.hkm.d4cshop.screens.shop.component.category.Category
import com.hkm.d4cshop.ui.theme.InStockGreen
import com.hkm.d4cshop.ui.theme.PrimaryText

@Composable
fun ShopScreen(
    viewModel: ShopViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState(ShopUiState.Loading)
    when(state){
        is ShopUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = InStockGreen
                )
            }
        }
        is ShopUiState.Error -> {
            val error = state as ShopUiState.Error
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = error.message ?: "Unknown error",style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryText))
            }
        }
        is ShopUiState.Success ->{
            val data = state as ShopUiState.Success
            AnimatedVisibility(
                visible = state != ShopUiState.Loading,
                enter = fadeIn(animationSpec = TweenSpec(1000))
            ) {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()) {
                    item {
                        if (data.banners.isNotEmpty()){
                            PromoBannerPager(bannerList = data.banners)
                        }
                    }
                    item{
                        Spacer(Modifier.height(16.dp))
                        Category(categoryList = data.categories)
                    }
                    item {
                        Spacer(Modifier.height(16.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                modifier = Modifier,
                                text = stringResource(R.string.new_products),
                                style = MaterialTheme.typography.headlineMedium.copy(color = PrimaryText)
                            )
                            Text(
                                modifier = Modifier.clickable {

                                },
                                text = stringResource(R.string.see_all),
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W400, fontSize = 18.sp,color = PrimaryText,textDecoration = TextDecoration.Underline, lineHeight = 22.sp)
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                    }

                    itemsIndexed(data.products, key = {index, item ->  item.id}){ index, item ->
                        if (index != 0) {
                            Spacer(Modifier.height(16.dp))
                        }
                        ItemCard(productData = item,onLikeClick = {
                            viewModel.updateProduct(it.copy(isLiked = !it.isLiked))
                        },onCartClick = {
                            viewModel.updateProduct(it.copy(isInCart = !it.isInCart))
                        },
                            modifier = Modifier.animateItem())
                    }
                    item {
                        Spacer(Modifier.height(16.dp))
                    }

                }

            }

        }
    }



}
@Preview
@Composable
fun ShopScreenPreview(){
    MaterialTheme {
        ShopScreen()
    }
}