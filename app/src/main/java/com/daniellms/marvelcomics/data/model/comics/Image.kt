package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("extension")
    var extension: String?,
    @SerializedName("path")
    var path: String?
)