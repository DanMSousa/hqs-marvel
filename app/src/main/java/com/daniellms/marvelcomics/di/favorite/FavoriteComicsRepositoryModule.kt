package com.daniellms.marvelcomics.di.favorite

import com.daniellms.marvelcomics.data.datasource.local.FavoriteComicsLocalDataSource
import com.daniellms.marvelcomics.data.repository.FavoriteComicsRepositoryImpl
import com.daniellms.marvelcomics.domain.repository.ComicsRepository
import com.daniellms.marvelcomics.domain.repository.FavoriteComicsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class FavoriteComicsRepositoryModule {

    @Provides
    fun provideFavoriteComicsRepository(localDataSource: FavoriteComicsLocalDataSource) : FavoriteComicsRepository {
        return FavoriteComicsRepositoryImpl(localDataSource)
    }

}