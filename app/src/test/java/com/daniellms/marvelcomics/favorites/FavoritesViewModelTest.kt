package com.daniellms.marvelcomics.favorites

import androidx.lifecycle.Observer
import com.daniellms.marvelcomics.BaseUnitTest
import com.daniellms.marvelcomics.data.model.comics.Comic
import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.daniellms.marvelcomics.domain.usecase.DeleteFavoriteComicUseCase
import com.daniellms.marvelcomics.domain.usecase.GetFavoriteComicsUseCase
import com.daniellms.marvelcomics.domain.usecase.SaveFavoriteComicUseCase
import com.daniellms.marvelcomics.ui.favorites.state.FavoritesState
import com.daniellms.marvelcomics.ui.favorites.viewmodel.FavoritesViewModel
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalStdlibApi
class FavoritesViewModelTest : BaseUnitTest() {

    private lateinit var favoritesViewModel: FavoritesViewModel

    @RelaxedMockK
    lateinit var getFavoriteComicsUseCase: GetFavoriteComicsUseCase

    @RelaxedMockK
    lateinit var saveFavoriteComicsUseCase: SaveFavoriteComicUseCase

    @RelaxedMockK
    lateinit var deleteFavoriteComicUseCase: DeleteFavoriteComicUseCase

    @RelaxedMockK
    lateinit var favoritesLiveDataObserver: Observer<FavoritesState>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        favoritesViewModel = FavoritesViewModel(
            saveFavoriteComicsUseCase,
            deleteFavoriteComicUseCase,
            getFavoriteComicsUseCase
        ).apply {
            favoriteMutableLiveData.observeForever(favoritesLiveDataObserver)
        }
    }

    @Test
    fun `WHEN call saveFavorite SHOULD give SavedSuccess`() = runTest {
        val comicFavoritedMockk = mockk<ComicFavorite>(relaxed = false)
        every { comicFavoritedMockk.idComic } returns 1

        val comicMockk = mockk<Comic>(relaxed = true)
        every { comicMockk.id } returns 1
        every { comicMockk.isFavorited } returns true
        every { comicMockk.comicFavorited } returns comicFavoritedMockk
        coEvery {
            saveFavoriteComicsUseCase.invoke(comicFavoritedMockk)
        } just Runs

        favoritesViewModel.saveFavorite(comicMockk)

        advanceUntilIdle()

        coVerifySequence {
            favoritesLiveDataObserver.onChanged(
                FavoritesState.StartToGetFavorites
            )

            favoritesLiveDataObserver.onChanged(
                FavoritesState.SavedFavorite
            )
        }
    }

    @Test
    fun `WHEN call deleteFavorite SHOULD give SuccessDeleted`() = runTest {
        val comicFavoritedMockk = mockk<ComicFavorite>(relaxed = false)
        every { comicFavoritedMockk.idComic } returns 1

        coEvery {
            deleteFavoriteComicUseCase.invoke(comicFavoritedMockk)
        } just Runs

        favoritesViewModel.deleteFavorite(comicFavoritedMockk)

        advanceUntilIdle()

        coVerifySequence {
            favoritesLiveDataObserver.onChanged(
                FavoritesState.StartToGetFavorites
            )

            favoritesLiveDataObserver.onChanged(
                FavoritesState.DeletedFavorite
            )
        }
    }

    @Test
    fun `WHEN call getAllFavorites SHOULD give SucccessGetComicsFavorite`() = runTest {
        val listComicFavoritesMockk = mockk<List<ComicFavorite>>(relaxed = true)
        coEvery {
            getFavoriteComicsUseCase.invoke()
        } returns listComicFavoritesMockk

        favoritesViewModel.getAllFavorites()

        advanceUntilIdle()

        coVerifySequence {
            favoritesLiveDataObserver.onChanged(
                FavoritesState.StartToGetFavorites
            )

            favoritesLiveDataObserver.onChanged(
                FavoritesState.GetComicsFavorite(listComicFavoritesMockk)
            )
        }
    }

}