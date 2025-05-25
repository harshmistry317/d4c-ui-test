package com.hkm.d4cshop.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_data",
    foreignKeys = [ForeignKey(
        entity = CategoryData::class,
        parentColumns = ["categoryId"], // Primary key of the parent table which is here from CategoryData
        childColumns = ["categoryId"], // Foreign key in the child table which is here from ProductData
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("categoryId")] // required for foreign key
)
data class ProductData(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val categoryId: Int, // foreign key referencing CategoryData.categoryId
    val isLiked: Boolean,
    val isInCart: Boolean,
    val isBestSeller: Boolean,
    val isInStock: Boolean,
    val productName: String,
    val productDescription: String,
    val productDescription2: String,
    val actualPrice: Double,
    val discountedPrice: Double,
    @DrawableRes val productImage: Int,
    val reviews: Int,
    val stars: Double,
)