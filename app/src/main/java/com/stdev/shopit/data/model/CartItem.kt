package com.stdev.shopit.data.model


import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("__v")
    val v: Int
)