package com.github.vitorfg8.popcorn.home.popularmovies.domain.usecase

import com.github.vitorfg8.popcorn.home.popularmovies.domain.repository.PopularMoviesRepository

class GetPopularMoviesUseCase(
    private val popularMoviesRepository: PopularMoviesRepository
) {
    suspend operator fun invoke() = popularMoviesRepository.getPopularMovies()
}