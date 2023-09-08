package com.github.vitorfg8.popcorn.details.data.service

import com.github.vitorfg8.popcorn.BuildConfig
import com.github.vitorfg8.popcorn.details.data.model.movie.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale

interface TvShowDetailsService {
    @GET("tv/{series_id}")
    suspend fun getTvShowDetails(
        @Path("series_id") seriesId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Locale.getDefault().toLanguageTag(),
    ): MovieDetailsResponse
}