package com.github.vitorfg8.popcorn.details.movie.domain.usecase

import com.github.vitorfg8.popcorn.details.movie.domain.repository.MovieDetailsRepository

class GetMovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) {
    suspend operator fun invoke(id: Int) = movieDetailsRepository.getMovieDetails(id)
}