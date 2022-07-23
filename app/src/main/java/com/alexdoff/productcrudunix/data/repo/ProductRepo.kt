package com.alexdoff.productcrudunix.data.repo

import com.alexdoff.productcrudunix.data.net.ProductApiInterface
import com.alexdoff.productcrudunix.data.net.RetrofitInstance

class ProductRepo(private val client: ProductApiInterface) {
    suspend fun getAllProduct() = client.getAllProduct()
}