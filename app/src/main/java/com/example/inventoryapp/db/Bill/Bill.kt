package com.example.inventoryapp.db.Bill

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_table")
data class Bill(
    @PrimaryKey(autoGenerate = true)
    val ID: String,
    val hashMap: HashMap<String, Int>,
    val total: String
)
