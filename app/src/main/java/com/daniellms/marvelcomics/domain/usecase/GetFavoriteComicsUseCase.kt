package com.daniellms.marvelcomics.domain.usecase

import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.daniellms.marvelcomics.domain.repository.FavoriteComicsRepository

class GetFavoriteComicsUseCase(
    private val favoriteComicsRepository: FavoriteComicsRepository
) {
    suspend fun invoke(): List<ComicFavorite> {
        return favoriteComicsRepository.getAllComics()
    }
}