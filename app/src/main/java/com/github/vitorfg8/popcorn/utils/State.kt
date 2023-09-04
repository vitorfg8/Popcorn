package com.github.vitorfg8.popcorn.utils

sealed class State<out T> {
    data class Error(val exception: Throwable) : State<Nothing>()
    object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
}
