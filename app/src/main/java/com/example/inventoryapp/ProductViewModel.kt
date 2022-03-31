package com.example.inventoryapp

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inventoryapp.db.Product.ProductRepository

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()
}