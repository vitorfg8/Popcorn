package com.github.vitorfg8.popcorn.details.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.popcorn.details.domain.usecase.DetailsUseCase
import com.github.vitorfg8.popcorn.details.ui.dataui.DetailsDataUi
import com.github.vitorfg8.popcorn.details.ui.mapper.toUi
import com.github.vitorfg8.popcorn.utils.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(private val detailsUseCase: DetailsUseCase) : ViewModel() {
    private val _details = MutableLiveData<State<DetailsDataUi>>()
    val details: LiveData<State<DetailsDataUi>>
        get() = _details

    fun getDetails(id: Int, mediaType: String) {
        viewModelScope.launch {
            detailsUseCase(id, mediaType).onStart {
                _details.value = State.Loading
            }.catch { error ->
                _details.value = State.Error(error)
            }.collect { details ->
                _details.value = State.Success(details.toUi())
            }
        }
    }
}