package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("date")
    var date: String?,
    @SerializedName("type")
    var type: String?
)