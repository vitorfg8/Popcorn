package com.github.vitorfg8.popcorn.details.cast.data.service

import com.github.vitorfg8.popcorn.BuildConfig
import com.github.vitorfg8.popcorn.details.cast.data.model.tvshow.TvShowCreditsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale

interface MovieCastService {
    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Locale.getDefault().toLanguageTag(),
    ): TvShowCreditsResponse
}