package com.daniellms.marvelcomics.ui.comics.state

import com.daniellms.marvelcomics.data.model.comics.Comic

sealed class ComicsState {
    object StartGet : ComicsState()
    data class SuccessGetComics(val listComics: List<Comic>) : ComicsState()
    object ErrorGetComics : ComicsState()
}