package com.daniellms.marvelcomics.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daniellms.marvelcomics.data.room.model.ComicFavorite

@Dao
interface ComicFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteComic(favorite: ComicFavorite)

    @Delete
    suspend fun deleteFavoriteComic(comicFavorite: ComicFavorite)

    @Query("SELECT * FROM comic_favorite_table")
    suspend fun getAllComics(): List<ComicFavorite>
}