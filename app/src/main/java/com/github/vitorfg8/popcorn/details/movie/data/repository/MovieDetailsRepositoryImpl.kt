package com.github.vitorfg8.popcorn.details.movie.data.repository

import com.github.vitorfg8.popcorn.details.movie.datasource.MovieDetailsRemoteDataSource
import com.github.vitorfg8.popcorn.details.movie.domain.mapper.toDomain
import com.github.vitorfg8.popcorn.details.movie.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.flow

class MovieDetailsRepositoryImpl(private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource) :
    MovieDetailsRepository {

    override suspend fun getMovieDetails(id: Int) = flow {
        emit(movieDetailsRemoteDataSource.getMovieDetails(id).toDomain())
    }
}