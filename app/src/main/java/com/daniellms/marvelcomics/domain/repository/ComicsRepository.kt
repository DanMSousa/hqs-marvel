package com.daniellms.marvelcomics.domain.repository

import com.daniellms.marvelcomics.data.model.comics.ResponseGetComics
import retrofit2.Response

interface ComicsRepository {

    suspend fun getComics(
        limit: Int
    ): Response<ResponseGetComics>

}