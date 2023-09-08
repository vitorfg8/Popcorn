package com.github.vitorfg8.popcorn.details.datasource

import com.github.vitorfg8.popcorn.details.data.service.TvShowDetailsService
import retrofit2.Retrofit

class TvShowDetailsRemoteDataSource(private val retrofit: Retrofit) {
    suspend fun getTvShowDetails(id: Int) =
        retrofit.create(TvShowDetailsService::class.java).getTvShowDetails(seriesId = id)
}