package com.github.vitorfg8.popcorn.details.tvshow.domain.usecase

import kotlinx.coroutines.flow.flow

class GetTvShowDetailsUseCase {
    operator fun invoke() = flow { emit(emptyList<String>()) } //TODO:
}