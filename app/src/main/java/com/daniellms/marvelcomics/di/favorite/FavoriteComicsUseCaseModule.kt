package com.daniellms.marvelcomics.di.favorite

import com.daniellms.marvelcomics.domain.repository.FavoriteComicsRepository
import com.daniellms.marvelcomics.domain.usecase.DeleteFavoriteComicUseCase
import com.daniellms.marvelcomics.domain.usecase.GetFavoriteComicsUseCase
import com.daniellms.marvelcomics.domain.usecase.SaveFavoriteComicUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FavoriteComicsUseCaseModule {

    @Provides
    fun provideSaveFavoriteComicUseCase(repository: FavoriteComicsRepository): SaveFavoriteComicUseCase {
        return SaveFavoriteComicUseCase(repository)
    }

    @Provides
    fun provideDeleteFavoriteComicUseCase(repository: FavoriteComicsRepository) : DeleteFavoriteComicUseCase {
        return DeleteFavoriteComicUseCase(repository)
    }

    @Provides
    fun provideGetAllFavoriteComics(repository: FavoriteComicsRepository) : GetFavoriteComicsUseCase {
        return GetFavoriteComicsUseCase(repository)
    }
}