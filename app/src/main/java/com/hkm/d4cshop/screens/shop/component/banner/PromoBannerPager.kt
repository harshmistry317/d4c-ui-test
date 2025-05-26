package com.hkm.d4cshop.screens.shop.component.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hkm.d4cshop.R
import com.hkm.d4cshop.models.BannerData
import com.hkm.d4cshop.ui.theme.DarkBackground
import com.hkm.d4cshop.ui.theme.InStockGreen
import com.hkm.d4cshop.utils.Utils
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@Composable
fun PromoBannerPager(modifier: Modifier = Modifier,
                     bannerList: List<BannerData>) {

    val pagerState = rememberPagerState(pageCount = { bannerList.size }, initialPage = 0)
    // Auto-scroll logic
    // Auto-scroll effect using a stable key
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % bannerList.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .clipToBounds()
                .graphicsLayer { clip = true },
            contentAlignment = Alignment.BottomStart

        ) {
            HorizontalPager(
                state = pagerState,
                pageSpacing = 0.dp,
                modifier = Modifier
            ) { page ->
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue

                val alpha = 1f - pageOffset.coerceIn(0f, 1f)
                val currentItem = bannerList[page]
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            this.alpha = alpha
                            scaleX = 0.95f + (0.05f * alpha)
                            scaleY = 0.95f + (0.05f * alpha)
                        }
                ) {
                   PromoBanner(
                       titleText = currentItem.titleText,
                       subTitleText = currentItem.subTitleText,
                       offerDate = Utils.formatDateRange(startMillis = currentItem.startTime, endMillis = currentItem.endTime),
                       productImage = currentItem.productImage
                   )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            PageIndicator(modifier = Modifier.offset(x = 70.dp, y = (-5).dp),currentPage = pagerState.currentPage, pageCount = bannerList.size)
        }





    }
}

@Composable
fun PageIndicator(modifier: Modifier = Modifier,currentPage: Int, pageCount: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(width = if (index == currentPage) 24.dp else 16.dp, height = 8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (index == currentPage) InStockGreen else DarkBackground
                    )
            )
        }
    }
}

@Preview
@Composable
fun PageIndicatorPreview() {
    PageIndicator(currentPage = 1, pageCount = 3)
}

@Preview
@Composable
fun PromoBannerPagerPreview(){
    MaterialTheme {
        val list = listOf(BannerData(
            titleText = "Get 20% Off",
            subTitleText = "Get 20% Off",
            startTime = 0L,
            endTime = 0L,
            productImage = R.drawable.product_image,
            bannerId = 1
        ),
            BannerData(
                titleText = "Get 25% Off",
                subTitleText = "Get 25% Off",
                startTime = 0L,
                endTime = 0L,
                productImage = R.drawable.product_image,
                bannerId = 2
            ),
            BannerData(
                titleText = "Get 30% Off",
                subTitleText = "Get 30% Off",
                startTime = 0L,
                endTime = 0L,
                productImage = R.drawable.product_image,
                bannerId = 3
            ),
            )
        PromoBannerPager(
            bannerList = list
        )
    }
}
