package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("type")
    var type: String?,
    @SerializedName("url")
    var url: String?
)