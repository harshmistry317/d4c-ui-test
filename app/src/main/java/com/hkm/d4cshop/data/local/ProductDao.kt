package com.hkm.d4cshop.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hkm.d4cshop.models.CategoryData
import com.hkm.d4cshop.models.ProductData
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    /**
     * Select all product from the product table.
     *
     * @return List of all ProductData.
     */
    @Query("SELECT * FROM product_data")
    fun getAllProduct(): Flow<List<ProductData>>

    /**
     * Insert or update a product in the database. If a task already exists, replace it.
     *
     * @param productData the task to be inserted or updated.
     */
    @Upsert
    suspend fun upsertProduct(productData: ProductData)

    /**
     * Insert or update a products in the database. If a task already exists, replace it.
     *
     * @param productDataList the task to be inserted or updated.
     */
    @Upsert
    suspend fun upsertAllProduct(productDataList: List<ProductData>)

    /**
     * Select ProductData by categoryId.
     *
     * @return List of all ProductData.
     */

    @Query("SELECT * FROM product_data WHERE categoryId = :categoryId")
    fun getProductsByCategoryId(categoryId: Int): Flow<List<ProductData>>

    /**
     * Select ProductData where isInCart is true.
     *
     * @return List of all ProductData.
     */
    @Query("SELECT * FROM product_data WHERE isInCart = 1")
    fun getProductsInCart(): Flow<List<ProductData>>

    /**
     * Select ProductData where isLiked is true.
     *
     * @return List of all ProductData.
     */
    @Query("SELECT * FROM product_data WHERE isLiked = 1")
    fun getProductsLiked(): Flow<List<ProductData>>

    /**
     * Remove all products from the cart.
     */
    @Query("UPDATE product_data SET isInCart = 0")
    suspend fun removeAllProductFromCart()

    /**
     * Remove all products from the liked.
     */

    @Query("UPDATE product_data SET isLiked = 0")
    suspend fun removeAllProductLiked()



}