package com.alexdoff.productcrudunix.data.obj

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductParcel(
    val createdAt: String?,
    val description: String?,
    val name: String?,
    val pid: String?,
    val price: String?,
    val updatedAt: String?
): Parcelable
