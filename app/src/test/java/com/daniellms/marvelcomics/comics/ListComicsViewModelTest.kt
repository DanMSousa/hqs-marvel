package com.daniellms.marvelcomics.comics

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cash.turbine.test
import com.daniellms.marvelcomics.BaseUnitTest
import com.daniellms.marvelcomics.data.model.comics.Comic
import com.daniellms.marvelcomics.data.model.comics.Data
import com.daniellms.marvelcomics.data.model.comics.ResponseGetComics
import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.daniellms.marvelcomics.domain.usecase.GetComicsUseCase
import com.daniellms.marvelcomics.domain.usecase.GetFavoriteComicsUseCase
import com.daniellms.marvelcomics.ui.comics.state.ComicsState
import com.daniellms.marvelcomics.ui.comics.viewmodel.ListComicsViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
class ListComicsViewModelTest : BaseUnitTest() {

    private lateinit var listComicsViewModel: ListComicsViewModel

    @RelaxedMockK
    lateinit var getComicsUseCase: GetComicsUseCase

    @RelaxedMockK
    lateinit var getFavoriteComicsUseCase: GetFavoriteComicsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        listComicsViewModel = ListComicsViewModel(getComicsUseCase, getFavoriteComicsUseCase)
    }

    @Test
    fun `WHEN call getComics SHOULD give SUCCESS`() = runTest {
        val LIMIT = 1

        val comicMockk = mockk<Comic>(relaxed = true)
        coEvery { comicMockk.id } returns 1

        val listComicsMockk = ArrayList<Comic>()
        listComicsMockk.add(comicMockk)

        val dataComicsMockk = mockk<Data>(relaxed = true)
        coEvery { dataComicsMockk.comics } returns listComicsMockk

        val responseGetComicsMockk = mockk<ResponseGetComics>(relaxed = true)
        coEvery { responseGetComicsMockk.dataComics } returns dataComicsMockk

        val responseGetComicsmockk = mockk<Response<ResponseGetComics>>(relaxed = true)
        coEvery { responseGetComicsmockk.code() } returns 200
        coEvery { responseGetComicsmockk.body() } returns responseGetComicsMockk

        coEvery {
            getComicsUseCase.invoke(LIMIT)
        } returns responseGetComicsmockk

        listComicsViewModel.getComics(LIMIT)

        val stateFlowComics = listComicsViewModel.comicsState.first { it is ComicsState.SuccessGetComics }

        assertEquals(ComicsState.SuccessGetComics(listComicsMockk), stateFlowComics)
    }

    @Test
    fun `WHEN call getComics SHOULD give ERROR`() = runTest {
        val LIMIT = 1

        val responseGetComicsmockk = mockk<Response<ResponseGetComics>>(relaxed = true)
        coEvery { responseGetComicsmockk.code() } returns 400

        coEvery {
            getComicsUseCase.invoke(LIMIT)
        } returns responseGetComicsmockk

        listComicsViewModel.getComics(LIMIT)

        val stateFlowComics = listComicsViewModel.comicsState.first { it is ComicsState.ErrorGetComics }

        assertEquals(ComicsState.ErrorGetComics, stateFlowComics)
    }

    @Test
    fun `WHEN call refreshListState SHOULD give SUCCESS`() = runTest {
        val LIMIT = 1

        val comicMockk = mockk<Comic>(relaxed = true)
        coEvery { comicMockk.id } returns 1
        coEvery { comicMockk.isFavorited } returns false

        val listComicsMockk = ArrayList<Comic>()
        listComicsMockk.add(comicMockk)

        val dataComicsMockk = mockk<Data>(relaxed = true)
        coEvery { dataComicsMockk.comics } returns listComicsMockk

        val responseGetComicsMockk = mockk<ResponseGetComics>(relaxed = true)
        coEvery { responseGetComicsMockk.dataComics } returns dataComicsMockk

        val responseGetComicsmockk = mockk<Response<ResponseGetComics>>(relaxed = true)
        coEvery { responseGetComicsmockk.code() } returns 200
        coEvery { responseGetComicsmockk.body() } returns responseGetComicsMockk

        coEvery {
            getComicsUseCase.invoke(LIMIT)
        } returns responseGetComicsmockk

        listComicsViewModel.getComics(LIMIT)

        val favoriteComicMockk = mockk<ComicFavorite>(relaxed = true)
        every { favoriteComicMockk.idComic } returns 1
        every { favoriteComicMockk.title } returns "homem aranha"
        every { favoriteComicMockk.variantDescription } returns "no multiverso do brasil"
        every { favoriteComicMockk.imageUrl } returns "https://imagem.com"
        every { favoriteComicMockk.price } returns 5.0

        val listComicFavorites = ArrayList<ComicFavorite>()
        listComicFavorites.add(favoriteComicMockk)

        coEvery { getFavoriteComicsUseCase.invoke() } returns listComicFavorites

        listComicsViewModel.refreshListState()

        val stateFlowComics = listComicsViewModel.comicsState.take(2).toList().last()

        assertEquals(1, (stateFlowComics as ComicsState.SuccessGetComics).listComics.size)
    }
}