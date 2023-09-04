package com.github.vitorfg8.popcorn.home.popularmovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.popcorn.home.popularmovies.domain.usecase.GetPopularMoviesUseCase
import com.github.vitorfg8.popcorn.home.popularmovies.ui.dataUi.PopularMovieDataUi
import com.github.vitorfg8.popcorn.home.popularmovies.ui.mapper.toUi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
    ViewModel() {

    private var _popularMovies = MutableLiveData<Result>()
    val popularMovies: LiveData<Result>
        get() = _popularMovies

    init {
        getTrends()
    }

    private fun getTrends() {
        viewModelScope.launch {
            getPopularMoviesUseCase().catch { error ->
                _popularMovies.value = Result.Error(error)
            }.collect { popularMovies ->
                _popularMovies.value = Result.Success(popularMovies.toUi())
            }
        }
    }
}

sealed class Result {
    data class Success(val list: List<PopularMovieDataUi>) : Result()
    data class Error(val exception: Throwable) : Result()
}

