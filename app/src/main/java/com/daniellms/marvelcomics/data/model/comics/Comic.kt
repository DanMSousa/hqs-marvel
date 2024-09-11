package com.daniellms.marvelcomics.data.model.comics


import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("characters")
    var characters: Characters?,
    @SerializedName("collectedIssues")
    var collectedIssues: List<CollectedIssue>?,
    @SerializedName("collections")
    var collections: List<Collection>?,
    @SerializedName("creators")
    var creators: Creators?,
    @SerializedName("dates")
    var dates: List<Date>?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("diamondCode")
    var diamondCode: String?,
    @SerializedName("digitalId")
    var digitalId: Int?,
    @SerializedName("ean")
    var ean: String?,
    @SerializedName("events")
    var events: Events?,
    @SerializedName("format")
    var format: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("images")
    var images: List<Image>?,
    @SerializedName("isbn")
    var isbn: String?,
    @SerializedName("issn")
    var issn: String?,
    @SerializedName("issueNumber")
    var issueNumber: Int?,
    @SerializedName("modified")
    var modified: String?,
    @SerializedName("pageCount")
    var pageCount: Int?,
    @SerializedName("prices")
    var prices: List<Price>?,
    @SerializedName("resourceURI")
    var resourceURI: String?,
    @SerializedName("series")
    var series: Series?,
    @SerializedName("stories")
    var stories: Stories?,
    @SerializedName("textObjects")
    var textObjects: List<TextObject>?,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("upc")
    var upc: String?,
    @SerializedName("urls")
    var urls: List<Url>?,
    @SerializedName("variantDescription")
    var variantDescription: String?,
    @SerializedName("variants")
    var variants: List<Variant>?
)