package com.example.inventoryapp.db.Product

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: Product) : Long

    @Update
    suspend fun updateProduct(product: Product) : Int

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): LiveData<List<Product>>

    @Delete
    suspend fun deleteProduct(product: Product) : Int

    @Query("DELETE FROM product_table")
    suspend fun deleteAll() : Int
}