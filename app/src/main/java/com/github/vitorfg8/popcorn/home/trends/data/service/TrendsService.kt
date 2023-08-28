package com.github.vitorfg8.popcorn.home.trends.data.service

import com.github.vitorfg8.popcorn.BuildConfig
import com.github.vitorfg8.popcorn.home.trends.data.model.TrendsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale

interface TrendsService {
    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path("media_type") mediaType: String = "all",
        @Path("time_window") list: String = "week",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Locale.getDefault().toLanguageTag()
    ): TrendsResponse
}