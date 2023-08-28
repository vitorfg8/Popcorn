package com.github.vitorfg8.popcorn.home.trends.datasource

import com.github.vitorfg8.popcorn.home.trends.data.service.TrendsService
import retrofit2.Retrofit

class TrendsRemoteDataSource(
    private val retrofit: Retrofit
) {
    suspend fun getTrends() = retrofit.create(TrendsService::class.java).getTrending()
}