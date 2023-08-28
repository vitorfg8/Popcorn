package com.github.vitorfg8.popcorn.home.trends.domain.usecase

import com.github.vitorfg8.popcorn.home.trends.domain.repository.TrendsRepository

class GetTrendsUseCase(
    private val trendsRepository: TrendsRepository
) {
    suspend operator fun invoke() = trendsRepository.getTrends()
}