package com.github.vitorfg8.popcorn.details.cast.domain.usecase

import com.github.vitorfg8.popcorn.details.cast.domain.repository.CastRepository

class GetCastUseCase(private val castRepository: CastRepository) {
    suspend operator fun invoke(mediaType: String, id: Int) = castRepository.getCast(mediaType, id)
}