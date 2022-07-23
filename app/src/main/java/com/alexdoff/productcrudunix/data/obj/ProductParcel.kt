package com.alexdoff.productcrudunix.data.obj

import android.os.Parcel
import android.os.Parcelable

data class ProductParcel(
    val createdAt: String?,
    val description: String?,
    val name: String?,
    val pid: String?,
    val price: String?,
    val updatedAt: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdAt)
        parcel.writeString(description)
        parcel.writeString(name)
        parcel.writeString(pid)
        parcel.writeString(price)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductParcel> {
        override fun createFromParcel(parcel: Parcel): ProductParcel {
            return ProductParcel(parcel)
        }

        override fun newArray(size: Int): Array<ProductParcel?> {
            return arrayOfNulls(size)
        }
    }
}
