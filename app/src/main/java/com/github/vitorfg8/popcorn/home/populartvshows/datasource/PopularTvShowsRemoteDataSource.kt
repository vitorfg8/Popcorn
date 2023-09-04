package com.github.vitorfg8.popcorn.home.populartvshows.datasource

import com.github.vitorfg8.popcorn.home.populartvshows.data.service.PopularTvShowsService
import retrofit2.Retrofit

class PopularTvShowsRemoteDataSource(private val retrofit: Retrofit) {
    suspend fun getPopularTvShows() =
        retrofit.create(PopularTvShowsService::class.java).getPopularTvShows()
}