package com.github.vitorfg8.popcorn.home.populartvshows.domain.repository

import com.github.vitorfg8.popcorn.home.populartvshows.domain.model.PopularTvShow
import kotlinx.coroutines.flow.Flow


interface PopularTvShowsRepository {
    suspend fun getPopularTvShows(): Flow<List<PopularTvShow>>
}