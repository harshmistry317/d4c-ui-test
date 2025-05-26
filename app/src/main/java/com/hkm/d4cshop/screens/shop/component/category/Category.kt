package com.hkm.d4cshop.screens.shop.component.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hkm.d4cshop.R
import com.hkm.d4cshop.models.CategoryData
import com.hkm.d4cshop.ui.theme.DarkBackground
import com.hkm.d4cshop.ui.theme.InStockGreen
import com.hkm.d4cshop.ui.theme.PrimaryText
import com.hkm.d4cshop.ui.theme.SecondaryText

@Composable
fun Category(
    onSeeAllClick: () -> Unit = {},
    categoryList: List<CategoryData>,
    selectedCategoryData: CategoryData?,
    onCategoryClick: (CategoryData) -> Unit
) {

    Column(

    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.categories),
                style = MaterialTheme.typography.headlineMedium.copy(color = PrimaryText)
            )
            Text(
                modifier = Modifier.clickable {
                    onSeeAllClick()
                },
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W400, fontSize = 18.sp,color = PrimaryText,textDecoration = TextDecoration.Underline, lineHeight = 22.sp)
            )
        }
        Spacer(Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp), //  space at start and end
            horizontalArrangement = Arrangement.spacedBy(16.dp) //  space between items
        ) {
            items(categoryList){category ->
                CategoryItem(categoryData = category, selectedCategoryData = selectedCategoryData, onCategoryClick = {
                    onCategoryClick(it)
                })
            }
        }
    }
}

@Preview
@Composable
fun PreviewCategory(){
    MaterialTheme {
        Category(
            categoryList = listOf(
                CategoryData(
                    categoryId = 1,
                    categoryName = "Cleaner",
                    categoryImage = R.drawable.category_sample
                ),
                CategoryData(
                    categoryId = 2,
                    categoryName = "Tonner",
                    categoryImage = R.drawable.product_image
                ),
                CategoryData(
                    categoryId = 3,
                    categoryName = "Serums",
                    categoryImage = R.drawable.category_sample
                ),
                CategoryData(
                    categoryId = 4,
                    categoryName = "SunsCreams",
                    categoryImage = R.drawable.product_image
                ),
            ),
            selectedCategoryData = CategoryData(
                categoryId = 1,
                categoryName = "Cleaner",
                categoryImage = R.drawable.category_sample
            ),
            onCategoryClick = {}
        )
    }
}

@Composable
fun CategoryItem(
    categoryData: CategoryData,
    selectedCategoryData: CategoryData?,
    onCategoryClick: (CategoryData) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .setBorder(selectedCategoryData?.categoryId == categoryData.categoryId)
                .background(DarkBackground)
                .clickable {
                    onCategoryClick(categoryData)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = categoryData.categoryImage),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = categoryData.categoryName,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W400, fontSize = 14.sp,color = PrimaryText)
        )
    }
}

@Preview
@Composable
fun PreviewCategoryItem(){
    MaterialTheme {
        CategoryItem(
            categoryData = CategoryData(
                categoryId = 1,
                categoryName = "Category Name",
                categoryImage = R.drawable.product_image),
            CategoryData(
                categoryId = 1,
                categoryName = "Category Name",
                categoryImage = R.drawable.product_image),
            onCategoryClick = {}
            )
    }
}

@Composable
private fun Modifier.setBorder(isSelected: Boolean): Modifier {
    return this then if (isSelected) {
        Modifier.border(
            2.dp, InStockGreen, RoundedCornerShape(100)
        )

    } else {
        Modifier.border(
            width = 2.dp,
            color =  Color.Transparent,
            shape = RoundedCornerShape(100)
        ) // Outer border

    }

}