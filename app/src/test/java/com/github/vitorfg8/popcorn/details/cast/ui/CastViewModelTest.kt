package com.github.vitorfg8.popcorn.details.cast.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.vitorfg8.popcorn.details.cast.domain.model.Cast
import com.github.vitorfg8.popcorn.details.cast.domain.usecase.GetCastUseCase
import com.github.vitorfg8.popcorn.details.cast.ui.dataui.CastDataUi
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
class CastViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()
    private val getCastUseCase: GetCastUseCase = mockk()
    private val castViewModel = CastViewModel(getCastUseCase)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        coEvery { getCastUseCase.invoke("movie", 2029) } returns flowOf(
            listOf(
                Cast(
                    character = "Logan",
                    id = 10,
                    name = "Hugh Jackman",
                    profilePath = "/x.jpg"
                )
            )
        )

    }

    @Test
    fun `When fetching content successfully Loading and Success are shown`() {
        val states = mutableListOf<State<List<CastDataUi>?>>()
        castViewModel.cast.observeForever { states.add(it) }
        castViewModel.getCast("movie", 2029)
        dispatcher.scheduler.advanceUntilIdle()
        assert(states[0] is State.Loading)
        assert(states[1] is State.Success)
    }

    @Test
    fun `When fetching content successfully data is mapped correctly`() {
        val popularTvShowDataUi = State.Success(
            listOf(
                CastDataUi(
                    character = "Logan",
                    id = 10,
                    name = "Hugh Jackman",
                    profileUrl = "https://image.tmdb.org/t/p/w300/x.jpg"
                )
            )
        )

        castViewModel.getCast("movie", 2029)
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(castViewModel.cast.value, popularTvShowDataUi)
    }

    @Test
    fun `When falling to fetching content Error is shown`() {
        coEvery { getCastUseCase.invoke("movie", 2029) } returns flow { throw Exception("") }
        castViewModel.getCast("movie", 2029)
        dispatcher.scheduler.advanceUntilIdle()
        assert(castViewModel.cast.value is State.Error)
    }

}