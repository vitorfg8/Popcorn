package com.github.vitorfg8.popcorn.home.trends.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.popcorn.home.trends.domain.model.Trend
import com.github.vitorfg8.popcorn.home.trends.domain.usecase.GetTrendsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TrendsViewModel(
    private val getTrendsUseCase: GetTrendsUseCase,
) : ViewModel() {

    private var _trends = MutableLiveData<Result>()
    val trends: LiveData<Result>
        get() = _trends


    init {
        getTrends()
    }

    private fun getTrends() {
        viewModelScope.launch {
            getTrendsUseCase().catch { error ->
                _trends.value = Result.Error(error)
            }.collect { trends ->
                _trends.value = Result.Success(trends)
            }
        }
    }
}

sealed class Result {
    data class Success(val list: List<Trend>) : Result()
    data class Error(val exception: Throwable) : Result()
}

