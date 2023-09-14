package com.github.vitorfg8.popcorn.details.cast.datasource

import com.github.vitorfg8.popcorn.details.cast.data.service.MovieCastService
import retrofit2.Retrofit

class MovieCastRemoteDataSource(private val retrofit: Retrofit) {
    suspend fun getCast(id: Int) = retrofit.create(MovieCastService::class.java).getCredits(id)
}