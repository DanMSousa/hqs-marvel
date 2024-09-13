package com.daniellms.marvelcomics.ui.favorites.state

import com.daniellms.marvelcomics.data.room.model.ComicFavorite

sealed class FavoritesState {
    data object StartToGetFavorites : FavoritesState()
    data object SavedFavorite : FavoritesState()
    data class GetComicsFavorite(val favorites: List<ComicFavorite>) : FavoritesState()
    data object DeletedFavorite : FavoritesState()
}