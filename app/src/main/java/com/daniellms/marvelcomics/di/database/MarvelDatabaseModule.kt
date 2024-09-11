package com.daniellms.marvelcomics.di.database

import android.content.Context
import androidx.room.Room
import com.daniellms.marvelcomics.data.room.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarvelDatabaseModule {

    @Singleton
    @Provides
    fun provideMarvelDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MarvelDatabase::class.java,
            "marvel_database"
        ).allowMainThreadQueries().build()
    }
}