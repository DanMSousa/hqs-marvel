package com.daniellms.marvelcomics.data.datasource.remote

import com.daniellms.marvelcomics.data.api.comics.ComicsApiService
import com.daniellms.marvelcomics.data.model.comics.ResponseGetComics
import retrofit2.Response

class ComicsRemoteDataSourceImpl(
    private val comicsApiService: ComicsApiService
) : ComicsRemoteDataSource {

    override suspend fun getComics(
        ts: String,
        apiKey: String,
        hash: String,
        limit: Int
    ): Response<ResponseGetComics> {
        return comicsApiService.getComics(ts, apiKey, hash, limit)
    }

}