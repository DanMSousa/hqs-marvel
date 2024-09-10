package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Creators(
    @SerializedName("available")
    var available: Int?,
    @SerializedName("collectionURI")
    var collectionURI: String?,
    @SerializedName("items")
    var items: List<com.daniellms.marvelcomics.data.model.comics.ItemX>?,
    @SerializedName("returned")
    var returned: Int?
)