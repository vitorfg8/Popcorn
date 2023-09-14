package com.github.vitorfg8.popcorn.details.cast.data.repository

import com.github.vitorfg8.popcorn.details.cast.datasource.MovieCastRemoteDataSource
import com.github.vitorfg8.popcorn.details.cast.datasource.TvShowCastRemoteDataSource
import com.github.vitorfg8.popcorn.details.cast.domain.mapper.toDomain
import com.github.vitorfg8.popcorn.details.cast.domain.repository.CastRepository
import com.github.vitorfg8.popcorn.utils.Constants
import kotlinx.coroutines.flow.flow

class CastRepositoryImpl(
    private val movieCastRemoteDataSource: MovieCastRemoteDataSource,
    private val tvShowCastRemoteDataSource: TvShowCastRemoteDataSource
) : CastRepository {
    override suspend fun getCast(mediaType: String, id: Int) = flow {
        if (mediaType == Constants.MOVIE) {
            emit(movieCastRemoteDataSource.getCast(id).toDomain())
        } else {
            emit(tvShowCastRemoteDataSource.getCast(id).toDomain())
        }
    }
}