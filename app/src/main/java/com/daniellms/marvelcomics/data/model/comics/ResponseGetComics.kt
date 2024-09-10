package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class ResponseGetComics(
    @SerializedName("attributionHTML")
    var attributionHTML: String?,
    @SerializedName("attributionText")
    var attributionText: String?,
    @SerializedName("code")
    var code: Int?,
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("data")
    var dataComics: Data?,
    @SerializedName("etag")
    var etag: String?,
    @SerializedName("status")
    var status: String?
)