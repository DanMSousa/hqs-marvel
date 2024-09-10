package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("name")
    var name: String?,
    @SerializedName("resourceURI")
    var resourceURI: String?
)