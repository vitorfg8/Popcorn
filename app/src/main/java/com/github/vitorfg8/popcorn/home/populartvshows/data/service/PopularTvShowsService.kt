package com.github.vitorfg8.popcorn.home.populartvshows.data.service

import com.github.vitorfg8.popcorn.BuildConfig
import com.github.vitorfg8.popcorn.home.populartvshows.data.model.PopularTvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale


interface PopularTvShowsService {
    @GET("tv/popular/")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Locale.getDefault().toLanguageTag(),
        @Query("region") region: String = Locale.getDefault().country
    ): PopularTvShowsResponse
}
