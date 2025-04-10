package com.usman.cvsproductsapp.data.model


import com.google.gson.annotations.SerializedName

data class ProductItemModel(
    val category: String = "",
    val description: String = "",
    val id: Int = 0,
    val image: String = "",
    val price: Double = 0.0,
    val rating: RatingModel = RatingModel(),
    val title: String = ""
)