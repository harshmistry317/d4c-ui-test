package com.hkm.d4cshop.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_data")
data class CategoryData(
    @PrimaryKey(autoGenerate = false) val categoryId : Int,
    val categoryName : String,
    @DrawableRes val categoryImage : Int
)