package com.github.vitorfg8.popcorn.details.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.vitorfg8.popcorn.details.domain.model.Details
import com.github.vitorfg8.popcorn.details.domain.usecase.GetDetailsUseCase
import com.github.vitorfg8.popcorn.details.ui.dataui.DetailsDataUi
import com.github.vitorfg8.popcorn.utils.State
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()
    private val getDetailsUseCase: GetDetailsUseCase = mockk()
    private val detailsViewModel = DetailsViewModel(getDetailsUseCase)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        coEvery { getDetailsUseCase.invoke(2029, "movie") } returns flowOf(
            Details(
                genres = listOf(),
                id = 2029,
                mediaType = "movie",
                posterPath = "/fnbjcRDYn6YviCcePDnGdyAkYsB.jpg",
                overview = "In the near future, a weary Logan cares for an ailing Professor X.",
                runtime = 137,
                title = "Logan",
                voteAverage = 10.0,
            )
        )

    }

    @Test
    fun `When fetching content successfully Loading and Success are shown`() {
        val states = mutableListOf<State<DetailsDataUi>>()
        detailsViewModel.details.observeForever { states.add(it) }
        detailsViewModel.getDetails(2029, "movie")
        dispatcher.scheduler.advanceUntilIdle()
        assert(states[0] is State.Loading)
        assert(states[1] is State.Success)
    }

    @Test
    fun `When fetching content successfully data is mapped correctly`() {
        val popularTvShowDataUi = State.Success(
            DetailsDataUi(
                genres = listOf(),
                id = 2029,
                mediaType = "movie",
                posterUrl = "https://image.tmdb.org/t/p/original//fnbjcRDYn6YviCcePDnGdyAkYsB.jpg",
                overview = "In the near future, a weary Logan cares for an ailing Professor X.",
                runtime = "2 h 17 m",
                title = "Logan",
                voteAverage = "10,0",
            )
        )

        detailsViewModel.getDetails(2029, "movie")
        dispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(detailsViewModel.details.value, popularTvShowDataUi)
    }

    @Test
    fun `When falling to fetching content Error is shown`() {
        coEvery { getDetailsUseCase.invoke(2029, "movie") } returns flow { throw Exception("") }
        detailsViewModel.getDetails(2029, "movie")
        dispatcher.scheduler.advanceUntilIdle()
        assert(detailsViewModel.details.value is State.Error)
    }

}
