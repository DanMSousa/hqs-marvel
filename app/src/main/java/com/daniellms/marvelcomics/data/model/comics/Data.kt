package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("limit")
    var limit: Int?,
    @SerializedName("offset")
    var offset: Int?,
    @SerializedName("results")
    var comics: List<Comic>?,
    @SerializedName("total")
    var total: Int?
)