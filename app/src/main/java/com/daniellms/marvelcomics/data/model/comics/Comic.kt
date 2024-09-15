package com.daniellms.marvelcomics.data.model.comics


import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("characters")
    var characters: Characters? = null,
    @SerializedName("collectedIssues")
    var collectedIssues: List<CollectedIssue>? = null,
    @SerializedName("collections")
    var collections: List<Collection>? = null,
    @SerializedName("creators")
    var creators: Creators? = null,
    @SerializedName("dates")
    var dates: List<Date>? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("diamondCode")
    var diamondCode: String? = null,
    @SerializedName("digitalId")
    var digitalId: Int? = null,
    @SerializedName("ean")
    var ean: String? = null,
    @SerializedName("events")
    var events: Events? = null,
    @SerializedName("format")
    var format: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("images")
    var images: List<Image>? = null,
    @SerializedName("isbn")
    var isbn: String? = null,
    @SerializedName("issn")
    var issn: String? = null,
    @SerializedName("issueNumber")
    var issueNumber: Int? = null,
    @SerializedName("modified")
    var modified: String? = null,
    @SerializedName("pageCount")
    var pageCount: Int? = null,
    @SerializedName("prices")
    var prices: List<Price>? = null,
    @SerializedName("resourceURI")
    var resourceURI: String? = null,
    @SerializedName("series")
    var series: Series? = null,
    @SerializedName("stories")
    var stories: Stories? = null,
    @SerializedName("textObjects")
    var textObjects: List<TextObject>? = null,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("upc")
    var upc: String? = null,
    @SerializedName("urls")
    var urls: List<Url>? = null,
    @SerializedName("variantDescription")
    var variantDescription: String? = null,
    @SerializedName("variants")
    var variants: List<Variant>? = null,
    var isFavorited: Boolean? = null,
    var comicFavorited: ComicFavorite? = null
) {

    fun getComicToFavorite() : ComicFavorite {
        return ComicFavorite(
            idComic = this.id,
            title = this.title,
            variantDescription = this.variantDescription,
            imageUrl = this.thumbnail?.path + "." + this.thumbnail?.extension,
            price = this.prices?.getOrNull(0)?.price ?: 0.0
        )
    }
}