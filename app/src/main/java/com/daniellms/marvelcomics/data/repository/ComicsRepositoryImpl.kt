package com.daniellms.marvelcomics.data.repository


import com.daniellms.core.HashUtils
import com.daniellms.marvelcomics.BuildConfig
import com.daniellms.marvelcomics.data.datasource.remote.ComicsRemoteDataSource
import com.daniellms.marvelcomics.data.model.comics.ResponseGetComics
import com.daniellms.marvelcomics.domain.repository.ComicsRepository
import retrofit2.Response

class ComicsRepositoryImpl(
    private val comicsRemoteDataSource: ComicsRemoteDataSource
) : ComicsRepository {

    override suspend fun getComics(
        limit: Int
    ): Response<ResponseGetComics> {
        val ts = HashUtils.getTimeStamp()
        val hashMd5 = HashUtils.generateHash(ts, BuildConfig.PRIVATE_API, BuildConfig.PUBLIC_API)
        return comicsRemoteDataSource.getComics(ts, BuildConfig.PUBLIC_API, hashMd5, limit)
    }
}