package com.example.inventoryapp.db.Product

class ProductRepository(private val dao: ProductDao) {
    val products = dao.getAllProducts()
    suspend fun insert(product: Product) {
        dao.insertProduct(product)
    }

    suspend fun update(product: Product) {
        dao.updateProduct(product)
    }

    suspend fun delete(product: Product) {
        dao.deleteProduct(product)
    }
}