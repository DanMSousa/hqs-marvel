package com.daniellms.marvelcomics.data.datasource.local

import com.daniellms.marvelcomics.data.room.dao.ComicFavoriteDao
import com.daniellms.marvelcomics.data.room.model.ComicFavorite

class FavoriteComicsLocalDataSourceImpl(
    private val favoriteDao: ComicFavoriteDao
) : FavoriteComicsLocalDataSource {

    override suspend fun saveFavoriteComic(favorite: ComicFavorite) {
        favoriteDao.saveFavoriteComic(favorite)
    }

    override suspend fun deleteFavoriteComic(comicFavorite: ComicFavorite) {
        favoriteDao.deleteFavoriteComic(comicFavorite)
    }

    override suspend fun getAllComics(): List<ComicFavorite> {
        return favoriteDao.getAllComics()
    }
}