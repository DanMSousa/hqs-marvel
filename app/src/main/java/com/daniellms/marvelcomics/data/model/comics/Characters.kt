package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("available")
    var available: Int?,
    @SerializedName("collectionURI")
    var collectionURI: String?,
    @SerializedName("items")
    var items: List<Item?>?,
    @SerializedName("returned")
    var returned: Int?
)