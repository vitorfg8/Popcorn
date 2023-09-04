package com.github.vitorfg8.popcorn.home.populartvshows.domain.repository

import com.github.vitorfg8.popcorn.home.populartvshows.domain.model.PopularTvShow
import kotlinx.coroutines.flow.Flow


interface PopularTvShowsRepository {
    fun getPopularTvShows(): Flow<List<PopularTvShow>>
}