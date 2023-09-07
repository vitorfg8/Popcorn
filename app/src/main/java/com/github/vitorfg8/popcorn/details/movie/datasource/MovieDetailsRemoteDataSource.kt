package com.github.vitorfg8.popcorn.details.movie.datasource

import com.github.vitorfg8.popcorn.details.movie.data.service.MovieDetailsService
import retrofit2.Retrofit

class MovieDetailsRemoteDataSource(private val retrofit: Retrofit) {
    suspend fun getMovieDetails(id: Int) =
        retrofit.create(MovieDetailsService::class.java).getMovieDetails(movieId = id)
}