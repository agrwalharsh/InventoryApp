package com.example.inventoryapp.db.Product

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    companion object{
        @Volatile
        private var INSTANCE: ProductDataBase? = null
        fun getDatabase(context: Context): ProductDataBase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDataBase::class.java,
                        "productDB"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }
    }
}