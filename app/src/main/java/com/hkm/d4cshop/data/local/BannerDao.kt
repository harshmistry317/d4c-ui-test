package com.hkm.d4cshop.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hkm.d4cshop.models.BannerData
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {

    /**
     * Select all banner from the banner table.
     *
     * @return List of all BannerData.
     **/
    @Query("SELECT * FROM banner_data")
    fun getAllBanner(): Flow<List<BannerData>>

    /**
     * Insert or update a banner in the database. If a task already exists, replace it.
     *
     * @param bannerData
     */
    @Upsert
    suspend fun upsertBanner(bannerData: BannerData)

    /**
     * Insert or update a banners in the database. If a task already exists, replace it.
     *
     * @param bannerDataList
     */
    @Upsert
    suspend fun upsertAllBanner(bannerDataList: List<BannerData>)
}