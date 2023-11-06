package com.github.vitorfg8.popcorn.home.popularmovies.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.vitorfg8.popcorn.home.popularmovies.domain.model.PopularMovie
import com.github.vitorfg8.popcorn.home.popularmovies.domain.usecase.GetPopularMoviesUseCase
import com.github.vitorfg8.popcorn.home.popularmovies.ui.dataUi.PopularMovieDataUi
import com.github.vitorfg8.popcorn.utils.State
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class PopularMoviesViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk()
    private val popularMoviesViewModel = PopularMoviesViewModel(getPopularMoviesUseCase)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        coEvery { getPopularMoviesUseCase.invoke() } returns flowOf(
            listOf(
                PopularMovie(
                    posterPath = "/wgpcgr.jpg",
                    id = 1610,
                    name = "Spider-Man: Across the Spider-Verse",
                )
            )
        )
    }

    @Test
    fun `When fetching content successfully Loading and Success are shown`() {
        val states = mutableListOf<State<List<PopularMovieDataUi>>>()
        popularMoviesViewModel.popularMovies.observeForever { states.add(it) }
        popularMoviesViewModel.getPopularMovies()
        dispatcher.scheduler.advanceUntilIdle()
        assert(states[0] is State.Loading)
        assert(states[1] is State.Success)
    }

    @Test
    fun `When fetching content successfully data is mapped correctly`() {
        val popularMovieDataUi = State.Success(
            listOf(
                PopularMovieDataUi(
                    posterUrl = "https://image.tmdb.org/t/p/w300/wgpcgr.jpg",
                    id = 1610,
                    title = "Spider-Man: Across the Spider-Verse",
                )
            )
        )

        popularMoviesViewModel.getPopularMovies()
        dispatcher.scheduler.advanceUntilIdle()
        TestCase.assertEquals(popularMoviesViewModel.popularMovies.value, popularMovieDataUi)
    }

    @Test
    fun `When falling to fetching content Error is shown`() {
        coEvery { getPopularMoviesUseCase.invoke() } returns flow { throw Exception("") }
        popularMoviesViewModel.getPopularMovies()
        dispatcher.scheduler.advanceUntilIdle()
        assert(popularMoviesViewModel.popularMovies.value is State.Error)
    }

}