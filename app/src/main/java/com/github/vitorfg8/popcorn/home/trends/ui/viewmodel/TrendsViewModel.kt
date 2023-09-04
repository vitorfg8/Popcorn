package com.github.vitorfg8.popcorn.home.trends.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.popcorn.home.trends.domain.usecase.GetTrendsUseCase
import com.github.vitorfg8.popcorn.home.trends.ui.dataUi.TrendDataUi
import com.github.vitorfg8.popcorn.home.trends.ui.mapper.toUi
import com.github.vitorfg8.popcorn.utils.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TrendsViewModel(private val getTrendsUseCase: GetTrendsUseCase) : ViewModel() {

    private var _trends = MutableLiveData<State<List<TrendDataUi>>>()
    val trends: LiveData<State<List<TrendDataUi>>>
        get() = _trends

    init {
        getTrends()
    }

    private fun getTrends() {
        viewModelScope.launch {
            getTrendsUseCase().catch { error ->
                _trends.value = State.Error(error)
            }.collect { trends ->
                _trends.value = State.Success(trends.toUi())
            }
        }
    }
}


