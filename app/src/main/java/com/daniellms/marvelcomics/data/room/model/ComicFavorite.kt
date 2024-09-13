package com.daniellms.marvelcomics.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic_favorite_table")
data class ComicFavorite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var idComic: Int?,
    var title: String?,
    var variantDescription: String?,
    var imageUrl: String?,
    var price: Double?
)