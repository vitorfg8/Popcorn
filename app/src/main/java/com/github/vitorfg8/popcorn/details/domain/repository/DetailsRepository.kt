package com.github.vitorfg8.popcorn.details.domain.repository

import com.github.vitorfg8.popcorn.details.domain.model.Details
import kotlinx.coroutines.flow.Flow


interface DetailsRepository {
    suspend fun getDetails(id: Int, mediaType: String): Flow<Details>
}