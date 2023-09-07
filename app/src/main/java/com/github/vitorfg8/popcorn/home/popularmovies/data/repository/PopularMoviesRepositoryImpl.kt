package com.github.vitorfg8.popcorn.home.popularmovies.data.repository

import com.github.vitorfg8.popcorn.home.popularmovies.datasource.PopularMoviesRemoteDataSource
import com.github.vitorfg8.popcorn.home.popularmovies.domain.mapper.toDomain
import com.github.vitorfg8.popcorn.home.popularmovies.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.flow

class PopularMoviesRepositoryImpl
    (private val popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource) :
    PopularMoviesRepository {
    override suspend fun getPopularMovies() = flow {
        emit(popularMoviesRemoteDataSource.getPopularMovies().toDomain())
    }
}