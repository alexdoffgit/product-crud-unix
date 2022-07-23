package com.alexdoff.productcrudunix.data.obj


import com.squareup.moshi.Json

data class GetProductsResponse(
    @Json(name = "products")
    val products: List<Product>,
    @Json(name = "success")
    val success: Int
)