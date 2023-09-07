package com.github.vitorfg8.popcorn.details.movie.domain.repository

import com.github.vitorfg8.popcorn.details.movie.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow


interface MovieDetailsRepository {
    suspend fun getMovieDetails(id: Int): Flow<MovieDetails>
}