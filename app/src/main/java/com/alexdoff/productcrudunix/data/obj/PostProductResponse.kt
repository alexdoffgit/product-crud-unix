package com.alexdoff.productcrudunix.data.obj


import com.squareup.moshi.Json

data class PostProductResponse(
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "updated_at")
    val updatedAt: String
)