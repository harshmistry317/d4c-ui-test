package com.hkm.d4cshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hkm.d4cshop.models.BannerData
import com.hkm.d4cshop.models.CategoryData
import com.hkm.d4cshop.models.ProductData

@Database(entities = [BannerData::class,
    CategoryData::class,
    ProductData::class],
    version = 1,
    exportSchema = true)
abstract class AppDataBase() : RoomDatabase() {

    abstract fun bannerDao(): BannerDao
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}