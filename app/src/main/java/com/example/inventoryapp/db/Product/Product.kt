package com.example.inventoryapp.db.Product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var ID: Long,
    var name: String,
    var quantity: Int,
)