package com.daniellms.marvelcomics.ui.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.daniellms.marvelcomics.data.model.comics.Comic
import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.daniellms.marvelcomics.domain.usecase.DeleteFavoriteComicUseCase
import com.daniellms.marvelcomics.domain.usecase.GetFavoriteComicsUseCase
import com.daniellms.marvelcomics.domain.usecase.SaveFavoriteComicUseCase
import com.daniellms.marvelcomics.ui.favorites.state.FavoritesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val saveFavoriteComicUseCase: SaveFavoriteComicUseCase,
    private val deleteFavoriteComicUseCase: DeleteFavoriteComicUseCase,
    private val getFavoriteComicsUseCase: GetFavoriteComicsUseCase
) : ViewModel() {

    private var _favoriteMutableLiveData = MutableLiveData<FavoritesState>(FavoritesState.StartToGetFavorites)
    val favoriteMutableLiveData : LiveData<FavoritesState> = _favoriteMutableLiveData

    fun saveFavorite(comic: Comic) = viewModelScope.launch {
        try {
            saveFavoriteComicUseCase.invoke(comic.getComicToFavorite())
            _favoriteMutableLiveData.value = FavoritesState.SavedFavorite
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteFavorite(comic: ComicFavorite) = viewModelScope.launch {
        try {
            deleteFavoriteComicUseCase.invoke(comic)
            _favoriteMutableLiveData.value = FavoritesState.DeletedFavorite
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllFavorites() = viewModelScope.launch {
        val favorites = getFavoriteComicsUseCase.invoke()
        _favoriteMutableLiveData.value = FavoritesState.GetComicsFavorite(favorites)
    }
}