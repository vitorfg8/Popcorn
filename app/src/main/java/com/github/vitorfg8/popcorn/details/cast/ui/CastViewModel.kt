package com.github.vitorfg8.popcorn.details.cast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.popcorn.details.cast.domain.usecase.GetCastUseCase
import com.github.vitorfg8.popcorn.details.cast.ui.dataui.CastDataUi
import com.github.vitorfg8.popcorn.details.cast.ui.mapper.toUi
import com.github.vitorfg8.popcorn.utils.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CastViewModel(private val getCastUseCase: GetCastUseCase) : ViewModel() {

    private val _cast = MutableLiveData<State<List<CastDataUi>?>>()
    val cast: LiveData<State<List<CastDataUi>?>>
        get() = _cast

    fun getCast(mediaType: String, id: Int) {
        viewModelScope.launch {
            getCastUseCase(mediaType, id).onStart {
                _cast.value = State.Loading
            }.catch { error ->
                _cast.value = State.Error(error)
            }.collect { cast ->
                _cast.value = State.Success(cast?.toUi())
            }
        }
    }

}