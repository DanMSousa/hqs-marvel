package com.daniellms.marvelcomics.di.comics

import com.daniellms.marvelcomics.data.datasource.remote.ComicsRemoteDataSource
import com.daniellms.marvelcomics.data.repository.ComicsRepositoryImpl
import com.daniellms.marvelcomics.domain.repository.ComicsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ComicsRepositoryModule {

    @Provides
    fun provideComicsRepository(comicsRemoteDataSource: ComicsRemoteDataSource): ComicsRepository {
        return ComicsRepositoryImpl(comicsRemoteDataSource)
    }

}