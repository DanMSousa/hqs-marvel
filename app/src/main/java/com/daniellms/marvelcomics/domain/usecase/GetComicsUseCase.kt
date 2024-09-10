package com.daniellms.marvelcomics.domain.usecase

import com.daniellms.marvelcomics.data.model.comics.ResponseGetComics
import com.daniellms.marvelcomics.domain.repository.ComicsRepository
import retrofit2.Response

class GetComicsUseCase(private val comicsRepository: ComicsRepository) {

    suspend fun invoke(limit: Int) : Response<ResponseGetComics> {
        return comicsRepository.getComics(limit)
    }

}