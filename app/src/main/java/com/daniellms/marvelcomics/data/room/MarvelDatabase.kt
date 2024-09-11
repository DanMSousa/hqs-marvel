package com.daniellms.marvelcomics.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.daniellms.marvelcomics.data.room.dao.ComicFavoriteDao
import com.daniellms.marvelcomics.data.room.model.ComicFavorite

@Database(entities = [ComicFavorite::class], version = 1, exportSchema = false)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun comicFavoriteDao(): ComicFavoriteDao
}