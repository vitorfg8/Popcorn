package com.github.vitorfg8.popcorn.details.cast.domain.repository

import com.github.vitorfg8.popcorn.details.cast.domain.model.Cast
import kotlinx.coroutines.flow.Flow

interface CastRepository {
    suspend fun getCast(mediaType: String, id: Int): Flow<List<Cast>?>
}