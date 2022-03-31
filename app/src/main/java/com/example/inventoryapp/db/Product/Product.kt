package com.example.inventoryapp.db.Product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val ID: String,
    val name: String,
    val price: Int,
    //val quantity: Int
)