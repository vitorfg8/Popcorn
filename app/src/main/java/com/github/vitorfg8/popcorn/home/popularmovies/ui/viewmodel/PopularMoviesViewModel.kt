package com.github.vitorfg8.popcorn.home.popularmovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.popcorn.home.popularmovies.domain.usecase.GetPopularMoviesUseCase
import com.github.vitorfg8.popcorn.home.popularmovies.ui.dataUi.PopularMovieDataUi
import com.github.vitorfg8.popcorn.home.popularmovies.ui.mapper.toUi
import com.github.vitorfg8.popcorn.utils.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
    ViewModel() {

    private var _popularMovies = MutableLiveData<State<List<PopularMovieDataUi>>>()
    val popularMovies: LiveData<State<List<PopularMovieDataUi>>>
        get() = _popularMovies

    init {
        getTrends()
    }

    private fun getTrends() {
        viewModelScope.launch {
            getPopularMoviesUseCase().catch { error ->
                _popularMovies.value = State.Error(error)
            }.collect { popularMovies ->
                _popularMovies.value = State.Success(popularMovies.toUi())
            }
        }
    }
}

