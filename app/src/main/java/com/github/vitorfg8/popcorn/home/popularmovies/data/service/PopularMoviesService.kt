package com.github.vitorfg8.popcorn.home.popularmovies.data.service

import com.github.vitorfg8.popcorn.BuildConfig
import com.github.vitorfg8.popcorn.home.popularmovies.data.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

interface PopularMoviesService {
    @GET("movie/popular/")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Locale.getDefault().toLanguageTag(),
        @Query("region") region: String = Locale.getDefault().country
    ): PopularMoviesResponse
}