package com.alexdoff.productcrudunix.data.obj


import com.squareup.moshi.Json

data class PutProductResponse(
    @Json(name = "products")
    val products: Product,
    @Json(name = "success")
    val success: Int
)