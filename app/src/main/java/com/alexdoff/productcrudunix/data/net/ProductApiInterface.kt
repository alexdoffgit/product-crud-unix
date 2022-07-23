package com.alexdoff.productcrudunix.data.net

import com.alexdoff.productcrudunix.data.obj.GetProductsResponse
import com.alexdoff.productcrudunix.data.obj.PostAndPutProductRequest
import com.alexdoff.productcrudunix.data.obj.PostProductResponse
import com.alexdoff.productcrudunix.data.obj.PutProductResponse
import retrofit2.Response
import retrofit2.http.*

interface ProductApiInterface {
    @GET("/api/product")
    suspend fun getAllProduct(): Response<GetProductsResponse>

    @POST("/api/product")
    suspend fun createProduct(@Body payload: PostAndPutProductRequest):
            Response<PostProductResponse>

    @PUT("/api/product/{id}")
    suspend fun updateProduct(@Path("id") id: String,
                              @Body payload: PostAndPutProductRequest):
            Response<PutProductResponse>

    @DELETE("/api/product/{id}")
    suspend fun deleteProduct(@Path("id") id: String): Response<String>
}