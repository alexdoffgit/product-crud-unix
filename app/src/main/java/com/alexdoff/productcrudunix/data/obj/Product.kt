package com.alexdoff.productcrudunix.data.obj


import com.squareup.moshi.Json

data class Product(
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "pid")
    val pid: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "updated_at")
    val updatedAt: String
)