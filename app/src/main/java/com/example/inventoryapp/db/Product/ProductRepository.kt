package com.example.inventoryapp.db.Product


class ProductRepository(private val dao: ProductDao) {
    val products = dao.getAllProducts()
    suspend fun insert(product: Product): Long {
        return dao.insertProduct(product)
    }

    suspend fun update(product: Product): Int {
        return dao.updateProduct(product)
    }

    suspend fun delete(product: Product): Int {
        return dao.deleteProduct(product)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}