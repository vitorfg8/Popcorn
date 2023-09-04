package com.github.vitorfg8.popcorn.home.populartvshows.data.repository

import com.github.vitorfg8.popcorn.home.populartvshows.datasource.PopularTvShowsRemoteDataSource
import com.github.vitorfg8.popcorn.home.populartvshows.domain.mapper.toDomain
import com.github.vitorfg8.popcorn.home.populartvshows.domain.repository.PopularTvShowsRepository
import kotlinx.coroutines.flow.flow

class PopularTvShowsRepositoryImpl(private val popularTvShowsRemoteDataSource: PopularTvShowsRemoteDataSource) :
    PopularTvShowsRepository {
    override fun getPopularTvShows() = flow {
        emit(popularTvShowsRemoteDataSource.getPopularTvShows().toDomain())
    }
}
