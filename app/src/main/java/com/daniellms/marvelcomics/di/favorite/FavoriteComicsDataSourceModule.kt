package com.daniellms.marvelcomics.di.favorite

import com.daniellms.marvelcomics.data.datasource.local.FavoriteComicsLocalDataSource
import com.daniellms.marvelcomics.data.datasource.local.FavoriteComicsLocalDataSourceImpl
import com.daniellms.marvelcomics.data.room.dao.ComicFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FavoriteComicsDataSourceModule {

    @Provides
    fun provideFavoriteLocalDataSource(favoriteDao: ComicFavoriteDao): FavoriteComicsLocalDataSource {
        return FavoriteComicsLocalDataSourceImpl(favoriteDao)
    }
}