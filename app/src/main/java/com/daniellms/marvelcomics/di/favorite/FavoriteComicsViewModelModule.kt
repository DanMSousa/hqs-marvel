package com.daniellms.marvelcomics.di.favorite

import com.daniellms.marvelcomics.domain.usecase.DeleteFavoriteComicUseCase
import com.daniellms.marvelcomics.domain.usecase.GetFavoriteComicsUseCase
import com.daniellms.marvelcomics.domain.usecase.SaveFavoriteComicUseCase
import com.daniellms.marvelcomics.ui.favorites.viewmodel.FavoritesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class FavoriteComicsViewModelModule {

    @Provides
    fun provideFavoriteComicsViewModel(saveFavoriteComicUseCase: SaveFavoriteComicUseCase,
                                       deleteFavoriteComicUseCase: DeleteFavoriteComicUseCase,
                                       getFavoriteComicsUseCase: GetFavoriteComicsUseCase
    ) : FavoritesViewModel {
        return FavoritesViewModel(saveFavoriteComicUseCase, deleteFavoriteComicUseCase, getFavoriteComicsUseCase)
    }
}