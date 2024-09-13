package com.daniellms.marvelcomics.ui.comics.state

import com.daniellms.marvelcomics.data.model.comics.Comic
import com.daniellms.marvelcomics.ui.favorites.state.FavoritesState

sealed class ComicsState {
    data object StartGet : ComicsState()
    data class SuccessGetComics(val listComics: List<Comic>) : ComicsState()
    data object ErrorGetComics : ComicsState()
}