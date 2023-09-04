package com.github.vitorfg8.popcorn.home.populartvshows.domain.usecases

import com.github.vitorfg8.popcorn.home.populartvshows.domain.repository.PopularTvShowsRepository

class GetPopularTvShowsUseCase(private val popularTvShowsRepository: PopularTvShowsRepository) {
    operator fun invoke() = popularTvShowsRepository.getPopularTvShows()
}