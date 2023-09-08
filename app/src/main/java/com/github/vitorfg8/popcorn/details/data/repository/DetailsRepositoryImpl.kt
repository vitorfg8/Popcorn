package com.github.vitorfg8.popcorn.details.data.repository

import com.github.vitorfg8.popcorn.details.datasource.MovieDetailsRemoteDataSource
import com.github.vitorfg8.popcorn.details.datasource.TvShowDetailsRemoteDataSource
import com.github.vitorfg8.popcorn.details.domain.mapper.toDomain
import com.github.vitorfg8.popcorn.details.domain.repository.DetailsRepository
import com.github.vitorfg8.popcorn.utils.Constants.MOVIE
import kotlinx.coroutines.flow.flow

class DetailsRepositoryImpl(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource,
    private val tvShowDetailsRemoteDataSource: TvShowDetailsRemoteDataSource
) :
    DetailsRepository {

    override suspend fun getDetails(id: Int, mediaType: String) = flow {
        if (mediaType == MOVIE) {
            emit(movieDetailsRemoteDataSource.getMovieDetails(id).toDomain())
        } else {
            emit(tvShowDetailsRemoteDataSource.getTvShowDetails(id).toDomain())
        }
    }
}