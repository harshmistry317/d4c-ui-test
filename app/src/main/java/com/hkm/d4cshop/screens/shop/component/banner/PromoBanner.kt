package com.hkm.d4cshop.screens.shop.component.banner

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hkm.d4cshop.R
import com.hkm.d4cshop.ui.theme.DarkBackground
import com.hkm.d4cshop.ui.theme.InStockGreen
import com.hkm.d4cshop.ui.theme.PrimaryText
import com.hkm.d4cshop.ui.theme.SecondaryText

@Composable
fun PromoBanner(
    modifier: Modifier = Modifier,
    titleText: String,
    subTitleText: String,
    offerDate: String,
    @DrawableRes productImage: Int
) {

    Box(
        modifier = modifier
            .wrapContentWidth()

    ) {

        Image(modifier = Modifier.fillMaxWidth()
            .wrapContentHeight(),
            contentScale = ContentScale.FillWidth, painter = painterResource(R.drawable.banner_card), contentDescription = "Banner Back Ground")

        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .padding(24.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = titleText,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 28.sp, color = PrimaryText)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Column(Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = subTitleText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W400, fontSize = 18.sp, color = SecondaryText)
                    )
                    Spacer(modifier = Modifier.height(16.dp).weight(1f))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(InStockGreen)
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(text = offerDate, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W400, fontSize = 16.sp, color = DarkBackground))
                    }
                    Spacer(modifier = Modifier.height(16.dp).weight(1f))
                }
                Image(
                    painter = painterResource(productImage),
                    contentDescription = "Promo Icon",
                    modifier = Modifier
                        .size(120.dp)

                )

            }

        }



    }
}

@Preview(showBackground = true)
@Composable
fun PrviewPromoBanner(){
    MaterialTheme {
        PromoBanner(
            titleText = "Get 20% Off",
            subTitleText = "Get 20% Off",
            offerDate = "20-25 oct",
            productImage = R.drawable.product_image
        )
    }
}
