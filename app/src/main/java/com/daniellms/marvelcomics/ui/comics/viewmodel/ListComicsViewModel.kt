package com.daniellms.marvelcomics.ui.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniellms.marvelcomics.data.model.comics.Comic
import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.daniellms.marvelcomics.domain.usecase.GetComicsUseCase
import com.daniellms.marvelcomics.domain.usecase.GetFavoriteComicsUseCase
import com.daniellms.marvelcomics.ui.comics.state.ComicsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListComicsViewModel(
    private val getComicsUseCase: GetComicsUseCase,
    private val getFavoriteComicsUseCase: GetFavoriteComicsUseCase
) : ViewModel() {
    private var isLoading = false

    private var _comicsState = MutableStateFlow<ComicsState>(ComicsState.StartGet)
    val comicsState : StateFlow<ComicsState?> get() = _comicsState.asStateFlow()

    private var _currentListComics = MutableStateFlow<List<Comic>>(emptyList())
    private val _countComics = MutableStateFlow(0)

    fun getComics(limit: Int) = viewModelScope.launch {
        if (isLoading) return@launch

        isLoading = true

        _countComics.value += limit

        if (_countComics.value > 100)
            _countComics.value = 100

        val response = getComicsUseCase.invoke(_countComics.value)
        when (response.code()) {
            200 -> {
                isLoading = false
                val responseBody = response.body()
                responseBody?.dataComics?.comics?.let { itComics ->
                    val listFavorites = getFavoriteComicsUseCase.invoke()

                    itComics.forEach { comic ->
                        listFavorites.forEach { itComicFavorited ->
                            if (comic.id == itComicFavorited.idComic) {
                                comic.isFavorited = true
                                comic.comicFavorited = itComicFavorited
                            }
                        }
                    }
                    _currentListComics.value = itComics
                    _comicsState.value = ComicsState.SuccessGetComics(itComics)
                }
            }
            else -> {
                isLoading = false
                _comicsState.value = ComicsState.ErrorGetComics
            }
        }
    }

    fun refreshListState() = viewModelScope.launch {
        val listFavorites = getFavoriteComicsUseCase.invoke()
        val newListComics = _currentListComics.value.map { comic ->
            if (comic.isFavorited == true) {
                val isIn = isComicInNewFavoritesUpdated(comic, listFavorites)

                if (isIn.not()) {
                    comic.copy(isFavorited = false, comicFavorited = null)
                } else {
                    comic.copy()
                }
            } else {
                val favoritedComic = listFavorites.find { it.idComic == comic.id }
                if (favoritedComic != null) {
                    comic.copy(isFavorited = true, comicFavorited = favoritedComic)
                } else {
                    comic.copy()
                }
            }
        }
        _currentListComics.update { newListComics }
        _comicsState.update{ ComicsState.SuccessGetComics(newListComics.toList()) }
    }

    private fun isComicInNewFavoritesUpdated(comic: Comic, listFavorites: List<ComicFavorite>): Boolean {
        return listFavorites.filter { it.idComic == comic.id }.isNotEmpty()

    }
}