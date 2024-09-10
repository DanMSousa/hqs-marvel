package com.daniellms.marvelcomics.di.comics

import com.daniellms.marvelcomics.domain.repository.ComicsRepository
import com.daniellms.marvelcomics.domain.usecase.GetComicsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ComicsUseCaseModule {

    @Provides
    fun provideUseCaseGetComics(comicsRepository: ComicsRepository): GetComicsUseCase {
        return GetComicsUseCase(comicsRepository)
    }


}