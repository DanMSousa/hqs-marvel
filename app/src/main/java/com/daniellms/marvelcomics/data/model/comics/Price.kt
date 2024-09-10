package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    var price: Double?,
    @SerializedName("type")
    var type: String?
)