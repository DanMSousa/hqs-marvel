package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("name")
    var name: String?,
    @SerializedName("resourceURI")
    var resourceURI: String?,
    @SerializedName("type")
    var type: String?
)