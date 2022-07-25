package com.alexdoff.productcrudunix.data.repo

import com.alexdoff.productcrudunix.data.net.ProductApiInterface
import com.alexdoff.productcrudunix.data.net.RetrofitInstance
import com.alexdoff.productcrudunix.data.obj.PostAndPutProductRequest
import com.alexdoff.productcrudunix.data.obj.PostProductResponse
import com.alexdoff.productcrudunix.data.obj.PutProductResponse
import retrofit2.Response

class ProductRepo(private val client: ProductApiInterface) {
    suspend fun getAllProduct() = client.getAllProduct()
    suspend fun createProduct(p: PostAndPutProductRequest): Response<PostProductResponse> {
        return client.createProduct(p)
    }
    suspend fun deleteProduct(id: String): Response<String> {
        return client.deleteProduct(id)
    }
    suspend fun updateProduct(id: String,
                              p: PostAndPutProductRequest):
            Response<PutProductResponse> {
        return client.updateProduct(id, p)
    }
}