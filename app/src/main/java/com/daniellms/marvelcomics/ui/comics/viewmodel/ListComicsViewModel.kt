package com.daniellms.marvelcomics.ui.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniellms.marvelcomics.data.model.comics.Comic
import com.daniellms.marvelcomics.domain.usecase.GetComicsUseCase
import com.daniellms.marvelcomics.ui.comics.state.ComicsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListComicsViewModel(
    private val getComicsUseCase: GetComicsUseCase
) : ViewModel() {

    private var currentPage = 0
    private var isLoading = false

    private val _comicsState = MutableStateFlow<ComicsState>(ComicsState.StartGet)
    val comicsState : StateFlow<ComicsState> get() = _comicsState

    private val _countComics = MutableStateFlow(0)

    fun getComics(limit: Int) = viewModelScope.launch {
        if (isLoading) return@launch

        isLoading = true

        _countComics.value += limit

        val response = getComicsUseCase.invoke(_countComics.value)
        when (response.code()) {
            200 -> {
                isLoading = false
                val responseBody = response.body()
                responseBody?.dataComics?.comics?.let { itComics ->
                    _comicsState.value = ComicsState.SuccessGetComics(itComics)
                }
            }
            else -> {
                isLoading = false
                _comicsState.value = ComicsState.ErrorGetComics
            }
        }
    }
}