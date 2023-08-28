package com.github.vitorfg8.popcorn.home.trends.domain.repository

import com.github.vitorfg8.popcorn.home.trends.domain.model.Trend
import kotlinx.coroutines.flow.Flow

interface TrendsRepository {
    suspend fun getTrends(): Flow<List<Trend>>
}