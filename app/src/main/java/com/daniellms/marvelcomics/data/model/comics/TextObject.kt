package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class TextObject(
    @SerializedName("language")
    var language: String?,
    @SerializedName("text")
    var text: String?,
    @SerializedName("type")
    var type: String?
)