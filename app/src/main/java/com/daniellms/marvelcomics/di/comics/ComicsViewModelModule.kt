package com.daniellms.marvelcomics.di.comics

import com.daniellms.marvelcomics.domain.usecase.GetComicsUseCase
import com.daniellms.marvelcomics.domain.usecase.GetFavoriteComicsUseCase
import com.daniellms.marvelcomics.ui.comics.viewmodel.ListComicsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ComicsViewModelModule {

    @Provides
    fun provideListComicsViewModel(
        getComicsUseCase: GetComicsUseCase,
        getFavoriteComicsUseCase: GetFavoriteComicsUseCase
    ): ListComicsViewModel {
        return ListComicsViewModel(getComicsUseCase, getFavoriteComicsUseCase)
    }
}