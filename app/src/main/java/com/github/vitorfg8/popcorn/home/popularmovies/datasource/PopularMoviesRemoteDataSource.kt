package com.github.vitorfg8.popcorn.home.popularmovies.datasource

import com.github.vitorfg8.popcorn.home.popularmovies.data.service.PopularMoviesService
import retrofit2.Retrofit

class PopularMoviesRemoteDataSource(
    private val retrofit: Retrofit,
) {
    suspend fun getPopularMovies() =
        retrofit.create(PopularMoviesService::class.java).getPopularMovies()
}