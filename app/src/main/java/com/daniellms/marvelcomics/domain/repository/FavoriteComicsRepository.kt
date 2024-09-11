package com.daniellms.marvelcomics.domain.repository

import com.daniellms.marvelcomics.data.room.model.ComicFavorite

interface FavoriteComicsRepository {
    suspend fun saveFavoriteComic(favorite: ComicFavorite)

    suspend fun deleteFavoriteComic(comicFavorite: ComicFavorite)

    suspend fun getAllComics(): List<ComicFavorite>
}