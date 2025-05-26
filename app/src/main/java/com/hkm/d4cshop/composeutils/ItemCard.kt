package com.hkm.d4cshop.composeutils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hkm.d4cshop.R
import com.hkm.d4cshop.models.ProductData
import com.hkm.d4cshop.ui.theme.DarkBackground
import com.hkm.d4cshop.ui.theme.DiscountPrice
import com.hkm.d4cshop.ui.theme.InStockGreen
import com.hkm.d4cshop.ui.theme.OutOfStockRed
import com.hkm.d4cshop.ui.theme.PrimaryText
import com.hkm.d4cshop.ui.theme.SecondaryText

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    productData: ProductData,
    onLikeClick:(ProductData) -> Unit,
    onCartClick:(ProductData) -> Unit
) {
    val price =
        if (productData.discountedPrice < productData.actualPrice) productData.discountedPrice else productData.actualPrice
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // Heart Icon
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    DarkBackground,
                    CircleShape
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // removes ripple effect
                ) {
                    onLikeClick(productData)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (productData.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Add to favorites",
                tint = DiscountPrice,
                modifier = Modifier.size(32.dp)
            )
        }
        Box() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(R.drawable.product_bg_card),
                contentDescription = "Product Back Ground"
            )
        }

        // Best Seller Badge
        if (productData.isBestSeller) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp)
                    .background(
                        DarkBackground,
                        CircleShape
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Best seller",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = InStockGreen
                    )
                )
            }
        }


        // Product image
        Box(
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.TopCenter)


        ) {
            Image(
                modifier = Modifier
                    .size(250.dp),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(productData.productImage),
                contentDescription = "Product image"
            )

        }

        //information
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    vertical = 16.dp,
                    horizontal = 16.dp
                )
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(R.drawable.product_title_card),
                contentDescription = "Product Back Ground"
            )

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(
                        vertical = 16.dp,
                        horizontal = 18.dp
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = productData.productName,
                        style = MaterialTheme.typography.headlineMedium.copy(color = InStockGreen)
                    )
                    Text(
                        text = if (productData.isInStock) "In Stock" else "Out of Stock",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            color = if (productData.isInStock) InStockGreen else OutOfStockRed
                        )
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = productData.productDescription,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        color = PrimaryText.copy(alpha = 0.8f),
                        lineHeight = 14.sp
                    ),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = productData.productDescription,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = PrimaryText,
                        lineHeight = 16.sp
                    ),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "RS.${price}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = DiscountPrice,
                            lineHeight = 18.sp
                        ),
                    )

                    if (productData.discountedPrice < productData.actualPrice) {
                        Text(
                            text = "RS.${productData.actualPrice}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = SecondaryText,
                                lineHeight = 16.sp
                            ),
                            textDecoration = TextDecoration.LineThrough
                        )
                    }


                }
                Spacer(Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarRatingBar(
                        rating = productData.stars.toFloat()
                    )
                    Text(
                        text = "${productData.reviews} reviews",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = SecondaryText,
                            lineHeight = 16.sp,
                            textDecoration = TextDecoration.Underline
                        ),

                    )

                }

            }
        }


        //cart
        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 16.dp)
                .size(56.dp)
                .border(2.dp, color = InStockGreen, shape = RoundedCornerShape(100))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // removes ripple effect
                ) {
                    onCartClick(productData)
                },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = if (productData.isInCart) Icons.Default.ShoppingCart else Icons.Outlined.ShoppingCart,
                contentDescription = "Add to cart",
                tint = InStockGreen,
                modifier = Modifier.size(32.dp)
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemCard() {
    MaterialTheme {
        ItemCard(
            productData = ProductData(
                id = 1,
                isLiked = true,
                isInCart = false,
                isBestSeller = true,
                isInStock = true,
                productName = "Product Name",
                productDescription = "Product Description",
                productDescription2 = "Product Description2",
                actualPrice = 350.2,
                discountedPrice = 250.5,
                productImage = R.drawable.product_image,
                reviews = 250,
                stars = 4.5,
                categoryId = 1
            ),
            onLikeClick = {},
            onCartClick = {}
        )
    }
}