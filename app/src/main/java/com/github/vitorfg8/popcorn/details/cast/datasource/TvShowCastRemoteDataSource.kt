package com.github.vitorfg8.popcorn.details.cast.datasource

import com.github.vitorfg8.popcorn.details.cast.data.service.TvShowCastService
import retrofit2.Retrofit

class TvShowCastRemoteDataSource(private val retrofit: Retrofit) {
    suspend fun getCast(id: Int) = retrofit.create(TvShowCastService::class.java).getCredits(id)
}