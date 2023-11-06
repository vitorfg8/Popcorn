package com.github.vitorfg8.popcorn.details.domain.usecase

import com.github.vitorfg8.popcorn.details.domain.repository.DetailsRepository

class GetDetailsUseCase(private val detailsRepository: DetailsRepository) {
    suspend operator fun invoke(id: Int, mediaType: String) =
        detailsRepository.getDetails(id, mediaType)
}