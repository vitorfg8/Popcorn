package com.github.vitorfg8.popcorn.home.populartvshows.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.vitorfg8.popcorn.home.populartvshows.domain.model.PopularTvShow
import com.github.vitorfg8.popcorn.home.populartvshows.domain.usecases.GetPopularTvShowsUseCase
import com.github.vitorfg8.popcorn.home.populartvshows.ui.dataUi.PopularTvShowDataUi
import com.github.vitorfg8.popcorn.utils.State
import io.mockk.coEvery
import io.mockk.mockk
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
class PopularTvShowsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase = mockk()
    private val popularTvShowsViewModel = PopularTvShowsViewModel(getPopularTvShowsUseCase)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        coEvery { getPopularTvShowsUseCase.invoke() } returns flowOf(
            listOf(
                PopularTvShow(
                    id = 0,
                    posterPath = "/l0k1.jpg",
                    name = "Loki"
                )
            )
        )
    }

    @Test
    fun `When fetching content successfully Loading and Success are shown`() {
        val states = mutableListOf<State<List<PopularTvShowDataUi>>>()
        popularTvShowsViewModel.popularTvShows.observeForever { states.add(it) }
        popularTvShowsViewModel.getPopularTvSeries()
        dispatcher.scheduler.advanceUntilIdle()
        assert(states[0] is State.Loading)
        assert(states[1] is State.Success)
    }

    @Test
    fun `When fetching content successfully data is mapped correctly`() {
        val popularTvShowDataUi = State.Success(
            listOf(
                PopularTvShowDataUi(
                    posterUrl = "https://image.tmdb.org/t/p/w300/l0k1.jpg",
                    id = 0,
                    title = "Loki",
                )
            )
        )

        popularTvShowsViewModel.getPopularTvSeries()
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(popularTvShowsViewModel.popularTvShows.value, popularTvShowDataUi)
    }

    @Test
    fun `When falling to fetching content Error is shown`() {
        coEvery { getPopularTvShowsUseCase.invoke() } returns flow { throw Exception("") }
        popularTvShowsViewModel.getPopularTvSeries()
        dispatcher.scheduler.advanceUntilIdle()
        assert(popularTvShowsViewModel.popularTvShows.value is State.Error)
    }

}