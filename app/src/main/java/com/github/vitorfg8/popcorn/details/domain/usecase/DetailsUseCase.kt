package com.github.vitorfg8.popcorn.details.domain.usecase

import com.github.vitorfg8.popcorn.details.domain.repository.DetailsRepository

class DetailsUseCase(private val detailsRepository: DetailsRepository) {
    suspend operator fun invoke(id: Int, mediaType: String) =
        detailsRepository.getDetails(id, mediaType)
}