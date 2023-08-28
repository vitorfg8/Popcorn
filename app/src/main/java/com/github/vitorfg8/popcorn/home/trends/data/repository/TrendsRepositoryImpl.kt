package com.github.vitorfg8.popcorn.home.trends.data.repository

import com.github.vitorfg8.popcorn.home.trends.datasource.TrendsRemoteDataSource
import com.github.vitorfg8.popcorn.home.trends.domain.mapper.toDomain
import com.github.vitorfg8.popcorn.home.trends.domain.repository.TrendsRepository
import kotlinx.coroutines.flow.flow

class TrendsRepositoryImpl(
    private val trendsRemoteDataSource: TrendsRemoteDataSource
) : TrendsRepository {
    override suspend fun getTrends() = flow {
        emit(trendsRemoteDataSource.getTrends().toDomain())
    }
}