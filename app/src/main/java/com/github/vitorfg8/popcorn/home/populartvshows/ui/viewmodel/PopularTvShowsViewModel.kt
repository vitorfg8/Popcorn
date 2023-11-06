package com.github.vitorfg8.popcorn.home.populartvshows.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.popcorn.home.populartvshows.domain.usecases.GetPopularTvShowsUseCase
import com.github.vitorfg8.popcorn.home.populartvshows.ui.dataUi.PopularTvShowDataUi
import com.github.vitorfg8.popcorn.home.populartvshows.ui.mapper.toUi
import com.github.vitorfg8.popcorn.utils.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PopularTvShowsViewModel(
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase
) : ViewModel() {

    private var _popularTvShows = MutableLiveData<State<List<PopularTvShowDataUi>>>()
    val popularTvShows: LiveData<State<List<PopularTvShowDataUi>>>
        get() = _popularTvShows

fun getPopularTvSeries() {
    viewModelScope.launch {
        getPopularTvShowsUseCase().onStart {
            _popularTvShows.value = State.Loading
        }.catch { error ->
            _popularTvShows.value = State.Error(error)
        }.collect { popularTvShows ->
            _popularTvShows.value = State.Success(popularTvShows.toUi())
        }
    }
}
}