package com.hkm.d4cshop.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hkm.d4cshop.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context,
                        callback: RoomDatabase.Callback): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "app_database"
        )
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideRoomCallback(): RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                db.execSQL("PRAGMA foreign_keys=ON") // ✅ enable foreign keys
            }
        }
    }
    @Provides
    fun provideBannerDao(database: AppDataBase) = database.bannerDao()

    @Provides
    fun provideCategoryDao(database: AppDataBase) = database.categoryDao()

    @Provides
    fun provideProductDao(database: AppDataBase) = database.productDao()

}