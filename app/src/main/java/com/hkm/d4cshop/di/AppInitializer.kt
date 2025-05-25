package com.hkm.d4cshop.di

import androidx.lifecycle.viewModelScope
import com.hkm.d4cshop.R
import com.hkm.d4cshop.data.local.BannerDao
import com.hkm.d4cshop.data.local.CategoryDao
import com.hkm.d4cshop.data.local.ProductDao
import com.hkm.d4cshop.models.BannerData
import com.hkm.d4cshop.models.CategoryData
import com.hkm.d4cshop.models.ProductData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInitializer @Inject constructor(
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val bannerDao: BannerDao
) {

    private val bannerData = listOf(
        BannerData(
            bannerId = 1,
            titleText = "Moisturizer",
            subTitleText = "Get 20% Off",
            startTime = 0L,
            endTime = 0L,
            productImage = R.drawable.product_image
        ),
        BannerData(
            bannerId = 2,
            titleText = "Toner",
            subTitleText = "Get 15% Off",
            startTime = 0L,
            endTime = 0L,
            productImage = R.drawable.category_sample
        ),
        BannerData(
            bannerId = 3,
            titleText = "Sunscreen",
            subTitleText = "Get 30% Off",
            startTime = 0L,
            endTime = 0L,
            productImage = R.drawable.product_image
        )
    )

    private val categoryData = listOf(
        CategoryData(
            categoryId = 1,
            categoryName = "Moisturizer",
            categoryImage = R.drawable.category_sample
        ),
        CategoryData(
            categoryId = 2,
            categoryName = "Toner",
            categoryImage = R.drawable.product_image
        ),
        CategoryData(
            categoryId = 3,
            categoryName = "Sunscreen",
            categoryImage = R.drawable.category_sample
        ),
        CategoryData(
            categoryId = 4,
            categoryName = "Serum",
            categoryImage = R.drawable.category_sample
        ),
        CategoryData(
            categoryId = 5,
            categoryName = "Cleanser",
            categoryImage = R.drawable.product_image
        ),
        CategoryData(
            categoryId = 6,
            categoryName = "Mask",
            categoryImage = R.drawable.product_image
        ),
    )

    val productData = listOf(
        // Moisturizer
        ProductData(
            id = 1,
            categoryId = 1,
            isLiked = true,
            isInCart = false,
            isBestSeller = true,
            isInStock = true,
            productName = "GlowNest",
            productDescription = "Deep hydration for glowing skin.",
            productDescription2 = "Enriched with hyaluronic acid and vitamins.",
            actualPrice = 1200.0,
            discountedPrice = 899.0,
            productImage = R.drawable.product_image,
            reviews = 156,
            stars = 4.5
        ),
        ProductData(
            id = 2,
            categoryId = 1,
            isLiked = false,
            isInCart = true,
            isBestSeller = false,
            isInStock = true,
            productName = "HydraSoft Cream",
            productDescription = "Soft cream for daily moisturizing.",
            productDescription2 = "Formulated for all skin types.",
            actualPrice = 950.0,
            discountedPrice = 749.0,
            productImage = R.drawable.category_sample,
            reviews = 88,
            stars = 4.3
        ),

        // Toner
        ProductData(
            id = 3,
            categoryId = 2,
            isLiked = false,
            isInCart = false,
            isBestSeller = true,
            isInStock = true,
            productName = "ClearDew",
            productDescription = "Refreshing toner for oily skin.",
            productDescription2 = "Balances pH and minimizes pores.",
            actualPrice = 800.0,
            discountedPrice = 599.0,
            productImage = R.drawable.product_image,
            reviews = 89,
            stars = 4.2
        ),
        ProductData(
            id = 4,
            categoryId = 2,
            isLiked = true,
            isInCart = false,
            isBestSeller = false,
            isInStock = true,
            productName = "RoseBalance Toner",
            productDescription = "Soothing toner with rose extract.",
            productDescription2 = "Reduces redness and refreshes skin.",
            actualPrice = 850.0,
            discountedPrice = 699.0,
            productImage = R.drawable.category_sample,
            reviews = 61,
            stars = 4.0
        ),

        // Sunscreen
        ProductData(
            id = 5,
            categoryId = 3,
            isLiked = true,
            isInCart = true,
            isBestSeller = true,
            isInStock = true,
            productName = "SunVeil",
            productDescription = "Lightweight SPF 50+ sunscreen.",
            productDescription2 = "Protects against UVA & UVB rays.",
            actualPrice = 950.0,
            discountedPrice = 750.0,
            productImage = R.drawable.category_sample,
            reviews = 210,
            stars = 4.8
        ),
        ProductData(
            id = 6,
            categoryId = 3,
            isLiked = false,
            isInCart = false,
            isBestSeller = false,
            isInStock = true,
            productName = "DailyShield",
            productDescription = "Matte finish sunscreen for daily use.",
            productDescription2 = "Non-greasy formula with broad-spectrum protection.",
            actualPrice = 890.0,
            discountedPrice = 690.0,
            productImage = R.drawable.product_image,
            reviews = 102,
            stars = 4.4
        ),

        // Serum
        ProductData(
            id = 7,
            categoryId = 4,
            isLiked = false,
            isInCart = false,
            isBestSeller = false,
            isInStock = true,
            productName = "NightBloom Elixir",
            productDescription = "Repairing night serum with retinol.",
            productDescription2 = "Reduces fine lines and pigmentation.",
            actualPrice = 1800.0,
            discountedPrice = 1299.0,
            productImage = R.drawable.category_sample,
            reviews = 134,
            stars = 4.4
        ),
        ProductData(
            id = 8,
            categoryId = 4,
            isLiked = true,
            isInCart = false,
            isBestSeller = true,
            isInStock = true,
            productName = "BrightAura",
            productDescription = "Vitamin C serum for brightening skin.",
            productDescription2 = "Fades dark spots and improves texture.",
            actualPrice = 1500.0,
            discountedPrice = 1100.0,
            productImage = R.drawable.product_image,
            reviews = 165,
            stars = 4.6
        ),
        ProductData(
            id = 9,
            categoryId = 4,
            isLiked = true,
            isInCart = true,
            isBestSeller = false,
            isInStock = false,
            productName = "HydraBoost Drops",
            productDescription = "Hydrating serum with niacinamide.",
            productDescription2 = "Improves skin elasticity and tone.",
            actualPrice = 1300.0,
            discountedPrice = 999.0,
            productImage = R.drawable.product_image,
            reviews = 77,
            stars = 4.1
        ),

        // Cleanser
        ProductData(
            id = 10,
            categoryId = 5,
            isLiked = true,
            isInCart = false,
            isBestSeller = true,
            isInStock = true,
            productName = "SmoothSilk",
            productDescription = "Gentle foaming face cleanser.",
            productDescription2 = "Removes impurities without dryness.",
            actualPrice = 700.0,
            discountedPrice = 499.0,
            productImage = R.drawable.product_image,
            reviews = 92,
            stars = 4.1
        ),
        ProductData(
            id = 11,
            categoryId = 5,
            isLiked = false,
            isInCart = false,
            isBestSeller = false,
            isInStock = true,
            productName = "AquaClean",
            productDescription = "Gel-based cleanser for oily skin.",
            productDescription2 = "Controls oil and unclogs pores.",
            actualPrice = 780.0,
            discountedPrice = 599.0,
            productImage = R.drawable.category_sample,
            reviews = 67,
            stars = 4.0
        ),

        // Mask
        ProductData(
            id = 12,
            categoryId = 6,
            isLiked = true,
            isInCart = true,
            isBestSeller = false,
            isInStock = true,
            productName = "ZenPore",
            productDescription = "Detoxifying clay mask for oily skin.",
            productDescription2 = "Shrinks pores and clears acne.",
            actualPrice = 1000.0,
            discountedPrice = 749.0,
            productImage = R.drawable.category_sample,
            reviews = 120,
            stars = 4.3
        ),
        ProductData(
            id = 13,
            categoryId = 6,
            isLiked = false,
            isInCart = false,
            isBestSeller = true,
            isInStock = true,
            productName = "GlowMud",
            productDescription = "Brightening mud mask with turmeric.",
            productDescription2 = "Gives instant radiance and clarity.",
            actualPrice = 1100.0,
            discountedPrice = 850.0,
            productImage = R.drawable.product_image,
            reviews = 98,
            stars = 4.5
        )
    )

    suspend fun checkAndInsertBanner() {

       val banners =  bannerDao.getAllBanner().first()
        if (banners.isEmpty()) {
            bannerDao.upsertAllBanner(bannerData)
        }


    }

    suspend fun checkAndInsertCategory() {

           val categories = categoryDao.getAllCategory().first()
        if (categories.isEmpty()) {
            categoryDao.upsertAllCategory(categoryData)
        }

    }

    suspend fun checkAndInsertProduct() {
       val categories =  categoryDao.getAllCategory().first()
        if (categories.isNotEmpty()) {
            val products = productDao.getAllProduct().first()
            if (products.isEmpty()) {
                productDao.upsertAllProduct(productData)
            }
        }



    }
}