package com.daniellms.marvelcomics.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic_favorite_table")
class ComicFavorite(
    @PrimaryKey val id: Int,
    val title: String?,
    val variantDescription: String?,
    val imageUrl: String?,
    var price: Double?
)