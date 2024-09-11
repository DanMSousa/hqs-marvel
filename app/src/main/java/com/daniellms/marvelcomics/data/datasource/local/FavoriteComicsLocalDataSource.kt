package com.daniellms.marvelcomics.data.datasource.local

import com.daniellms.marvelcomics.data.room.model.ComicFavorite

interface FavoriteComicsLocalDataSource {

    suspend fun saveFavoriteComic(favorite: ComicFavorite)

    suspend fun deleteFavoriteComic(comicFavorite: ComicFavorite)

    suspend fun getAllComics(): List<ComicFavorite>
}