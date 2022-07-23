package com.alexdoff.productcrudunix.data.obj


import com.squareup.moshi.Json

data class PostAndPutProductRequest(
    @Json(name = "description")
    val description: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Double
)