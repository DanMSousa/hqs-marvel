package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("name")
    var name: String?,
    @SerializedName("resourceURI")
    var resourceURI: String?
)