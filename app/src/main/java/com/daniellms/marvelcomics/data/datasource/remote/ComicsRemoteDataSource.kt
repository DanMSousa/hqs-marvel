package com.daniellms.marvelcomics.data.datasource.remote

import com.daniellms.marvelcomics.data.model.comics.ResponseGetComics
import retrofit2.Response

interface ComicsRemoteDataSource {

    suspend fun getComics(
        ts: String,
        apiKey: String,
        hash: String,
        limit: Int
    ): Response<ResponseGetComics>

}