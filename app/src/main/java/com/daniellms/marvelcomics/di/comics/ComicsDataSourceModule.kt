package com.daniellms.marvelcomics.di.comics

import com.daniellms.marvelcomics.data.api.comics.ComicsApiService
import com.daniellms.marvelcomics.data.datasource.remote.ComicsRemoteDataSource
import com.daniellms.marvelcomics.data.datasource.remote.ComicsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ComicsDataSourceModule {

    @Provides
    fun provideComicsRemoteDataSource(apiService: ComicsApiService): ComicsRemoteDataSource {
        return ComicsRemoteDataSourceImpl(apiService)
    }
}