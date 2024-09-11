package com.daniellms.marvelcomics.di.database

import com.daniellms.marvelcomics.data.room.MarvelDatabase
import com.daniellms.marvelcomics.data.room.dao.ComicFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ComicFavoriteDaoModule {

    @Provides
    fun provideComicFavoritesDao(database: MarvelDatabase): ComicFavoriteDao {
        return database.comicFavoriteDao()
    }
}