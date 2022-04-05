package com.example.inventoryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryapp.db.Product.ProductRepository

class ProductViewModelFactory(private val repository: ProductRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}