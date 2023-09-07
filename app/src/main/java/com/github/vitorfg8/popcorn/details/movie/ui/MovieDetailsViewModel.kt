package com.github.vitorfg8.popcorn.details.movie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.popcorn.details.movie.domain.usecase.GetMovieDetailsUseCase
import com.github.vitorfg8.popcorn.details.movie.ui.dataui.MovieDetailsDataUi
import com.github.vitorfg8.popcorn.details.movie.ui.mapper.toUi
import com.github.vitorfg8.popcorn.utils.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieDetailsUseCase: GetMovieDetailsUseCase) : ViewModel() {
    private val _movieDetails = MutableLiveData<State<MovieDetailsDataUi>>()
    val movieDetails: LiveData<State<MovieDetailsDataUi>>
        get() = _movieDetails

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            movieDetailsUseCase(id).catch { error ->
                _movieDetails.value = State.Error(error)
            }.collect { details ->
                _movieDetails.value = State.Success(details.toUi())
            }
        }
    }
}