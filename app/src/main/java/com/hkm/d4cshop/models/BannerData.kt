package com.hkm.d4cshop.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banner_data")
data class BannerData(
    @PrimaryKey(autoGenerate = false) val bannerId: Int,
    val titleText: String,
    val subTitleText: String,
    val startTime: Long,
    val endTime: Long,
    @DrawableRes val productImage: Int
)
