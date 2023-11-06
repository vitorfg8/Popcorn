package com.github.vitorfg8.popcorn.home.trends.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.vitorfg8.popcorn.home.trends.domain.model.Trend
import com.github.vitorfg8.popcorn.home.trends.domain.usecase.GetTrendsUseCase
import com.github.vitorfg8.popcorn.home.trends.ui.dataUi.TrendDataUi
import com.github.vitorfg8.popcorn.utils.State
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class TrendsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()
    private val getTrendsUseCase: GetTrendsUseCase = mockk()
    private val trendsViewModel = TrendsViewModel(getTrendsUseCase)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        coEvery { getTrendsUseCase.invoke() } returns flowOf(
            listOf(
                Trend(
                    backdropPath = "/wgpcgr.jpg",
                    id = 1610,
                    mediaType = "movie",
                    title = "Spider-Man: Across the Spider-Verse",
                    voteAverage = 10.0
                )
            )
        )
    }

    @Test
    fun `When fetching content successfully Loading and Success are shown`() {
        val states = mutableListOf<State<List<TrendDataUi>>>()
        trendsViewModel.trends.observeForever { states.add(it) }
        trendsViewModel.getTrends()
        dispatcher.scheduler.advanceUntilIdle()
        assert(states[0] is State.Loading)
        assert(states[1] is State.Success)
    }

    @Test
    fun `When fetching content successfully data is mapped correctly`() {
        val trendsDataUi = State.Success(
            listOf(
                TrendDataUi(
                    backdropUrl = "https://image.tmdb.org/t/p/original/wgpcgr.jpg",
                    id = 1610,
                    mediaType = "movie",
                    title = "Spider-Man: Across the Spider-Verse",
                    voteAverage = "10,0"
                )
            )
        )

        trendsViewModel.getTrends()
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(trendsViewModel.trends.value, trendsDataUi)
    }

    @Test
    fun `When falling to fetching content Error is shown`() {
        coEvery { getTrendsUseCase.invoke() } returns flow { throw Exception("") }
        trendsViewModel.getTrends()
        dispatcher.scheduler.advanceUntilIdle()
        assert(trendsViewModel.trends.value is State.Error)
    }
}