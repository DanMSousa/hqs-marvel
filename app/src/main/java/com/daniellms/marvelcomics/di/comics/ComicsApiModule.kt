package com.daniellms.marvelcomics.di.comics

import com.daniellms.marvelcomics.data.api.comics.ComicsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class ComicsApiModule {

    @Provides
    fun provideGetComicsApiService(retrofit: Retrofit): ComicsApiService {
        return retrofit.create(ComicsApiService::class.java)
    }

}