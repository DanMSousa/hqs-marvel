package com.daniellms.marvelcomics.data.api.comics

import com.daniellms.marvelcomics.data.model.comics.ResponseGetComics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsApiService {

    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ): Response<ResponseGetComics>
}