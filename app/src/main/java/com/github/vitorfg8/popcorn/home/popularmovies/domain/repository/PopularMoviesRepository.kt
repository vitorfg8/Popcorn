package com.github.vitorfg8.popcorn.home.popularmovies.domain.repository

import com.github.vitorfg8.popcorn.home.popularmovies.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    suspend fun getPopularMovies(): Flow<List<PopularMovie>>
}