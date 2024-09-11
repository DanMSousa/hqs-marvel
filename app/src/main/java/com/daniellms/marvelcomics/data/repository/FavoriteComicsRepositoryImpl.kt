package com.daniellms.marvelcomics.data.repository

import com.daniellms.marvelcomics.data.datasource.local.FavoriteComicsLocalDataSource
import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.daniellms.marvelcomics.domain.repository.FavoriteComicsRepository

class FavoriteComicsRepositoryImpl(
    private val favoriteComicsLocalDataSource: FavoriteComicsLocalDataSource
) : FavoriteComicsRepository {

    override suspend fun saveFavoriteComic(favorite: ComicFavorite) {
        favoriteComicsLocalDataSource.saveFavoriteComic(favorite)
    }

    override suspend fun deleteFavoriteComic(comicFavorite: ComicFavorite) {
        favoriteComicsLocalDataSource.deleteFavoriteComic(comicFavorite)
    }

    override suspend fun getAllComics(): List<ComicFavorite> {
        return favoriteComicsLocalDataSource.getAllComics()
    }
}