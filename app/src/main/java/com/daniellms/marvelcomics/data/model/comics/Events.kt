package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("available")
    var available: Int?,
    @SerializedName("collectionURI")
    var collectionURI: String?,
    @SerializedName("items")
    var items: List<Any?>?,
    @SerializedName("returned")
    var returned: Int?
)