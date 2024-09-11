package com.daniellms.marvelcomics.domain.usecase

import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.daniellms.marvelcomics.domain.repository.FavoriteComicsRepository

class SaveFavoriteComicUseCase(
    private val comicsRepository: FavoriteComicsRepository
) {
    suspend fun invoke(comicFavorite: ComicFavorite) {
        comicsRepository.saveFavoriteComic(comicFavorite)
    }
}