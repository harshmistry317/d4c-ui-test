package com.hkm.d4cshop.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.hkm.d4cshop.models.CategoryData
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    /**
     * Select all category from the category table.
     *
     * @return List of all CategoryData.
     */
    @Query("SELECT * FROM category_data")
    fun getAllCategory(): Flow<List<CategoryData>>


    /**
     * Insert or update a category in the database. If a task already exists, replace it.
     *
     * @param categoryData the task to be inserted or updated.
     */
    @Upsert
    suspend fun upsertCategory(categoryData: CategoryData)

    /**
     * Insert or update a categorys in the database. If a task already exists, replace it.
     *
     * @param categoryDataList the task to be inserted or updated.
     */
    @Upsert
    suspend fun upsertAllCategory(categoryDataList: List<CategoryData>)



}