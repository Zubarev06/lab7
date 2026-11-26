package com.example.roomdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProductRepository(private val productDao: ProductDao) {
    fun insertProduct(product: Product) {}
    fun findProduct(name: String) {}
    fun deleteProduct(name: String) {}

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
    val searchResults = MutableLiveData<List<Product>>()
}

val searchResults = MutableLiveData<List<Product>>()
private val coroutineScope = CoroutineScope(Dispatchers.Main)

fun insertProduct(newproduct: Product, insertProduct: Nothing?.(Product) -> Unit) {
    coroutineScope.launch(Dispatchers.IO) {
        val productDao = null
        productDao.insertProduct(newproduct)
    }
}

fun deleteProduct(name: String, deleteProduct: Nothing?.(String) -> Unit) {
    coroutineScope.launch(Dispatchers.IO) {
        val productDao = null
        productDao.deleteProduct(name)
    }
}

fun findProduct(name: String, await: Deferred<List<Product>?>.() -> List<Product>?) {
    coroutineScope.launch(Dispatchers.Main) {
        searchResults.value = asyncFind(
            name,
            findProduct = TODO(),
        ).await()
    }
}

private fun asyncFind(name: String, findProduct: Nothing?.(String) -> List<Product>?): Deferred<List<Product>?> =
    coroutineScope.async(Dispatchers.IO) {
        val productDao = null
        return@async productDao.findProduct(name)
    }
