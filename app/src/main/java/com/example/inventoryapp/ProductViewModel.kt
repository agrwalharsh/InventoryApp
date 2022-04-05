package com.example.inventoryapp

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryapp.db.Product.Product
import com.example.inventoryapp.db.Product.ProductRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel(), Observable {

    val productList = repository.products
    private var isUpdateOrDelete = false
    private lateinit var productToUpdateOrDelete: Product
    val inputName = MutableLiveData<String?>()

    val inputQuantity = MutableLiveData<String?>()

    val saveOrUpdateButtonText = MutableLiveData<String>()

    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (inputName.value == null) {
            statusMessage.value = Event("Please enter Product's name")
        } else if (inputQuantity.value == null) {
            statusMessage.value = Event("Please enter Product's quantity")
        } else {
            if (isUpdateOrDelete) {
                productToUpdateOrDelete.name = inputName.value!!
                productToUpdateOrDelete.quantity = inputQuantity.value?.toInt()!!
                update(productToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val quantity = inputQuantity.value!!
                insert(Product(0, name, quantity.toInt()))
                inputName.value = null
                inputQuantity.value = null
            }
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(productToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    private fun insert(product: Product): Job =
        viewModelScope.launch {
            val newRowID = repository.insert(product)
            if (newRowID > -1) {
                statusMessage.value = Event("Product Added Successfully")
            } else {
                statusMessage.value = Event("Error Occurred")
            }
        }


    private fun update(product: Product) {
        viewModelScope.launch {
            val noOfRows = repository.update(product)
            if (noOfRows > 0) {
                inputName.value = null
                inputQuantity.value = null
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("Product Updated Successfully")
            } else {
                statusMessage.value = Event("Error Occurred")
            }
        }
    }

    private fun delete(product: Product) {
        viewModelScope.launch {
            val noOfRowsDeleted = repository.delete(product)
            if (noOfRowsDeleted > 0) {
                inputName.value = null
                inputQuantity.value = null
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("$noOfRowsDeleted Product Deleted Successfully")
            } else {
                statusMessage.value = Event("Error Occurred")
            }
        }
    }

    private fun clearAll(): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted Products Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun initUpdateAndDelete(product: Product) {
        inputName.value = product.name
        inputQuantity.value = product.quantity.toString()
        isUpdateOrDelete = true
        productToUpdateOrDelete = product
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}