package com.daniellms.marvelcomics.di.network

import com.daniellms.marvelcomics.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideClientOkHttp(): OkHttpClient {
        val SIXTY_SECONDS = 60L

        return OkHttpClient.Builder()
            .addInterceptor { itChain ->
                val request = itChain.request().newBuilder()
                    .build()
                itChain.proceed(request)
            }
            .connectTimeout(SIXTY_SECONDS, TimeUnit.SECONDS)
            .readTimeout(SIXTY_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(SIXTY_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient) : Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }
}
