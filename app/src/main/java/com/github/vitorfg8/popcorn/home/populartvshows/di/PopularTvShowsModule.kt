package com.github.vitorfg8.popcorn.home.populartvshows.di

import com.github.vitorfg8.popcorn.home.populartvshows.data.repository.PopularTvShowsRepositoryImpl
import com.github.vitorfg8.popcorn.home.populartvshows.datasource.PopularTvShowsRemoteDataSource
import com.github.vitorfg8.popcorn.home.populartvshows.domain.usecases.GetPopularTvShowsUseCase
import com.github.vitorfg8.popcorn.home.populartvshows.ui.viewmodel.PopularTvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val popularTvShowsModule = module {
    viewModel {
        PopularTvShowsViewModel(
            getPopularTvShowsUseCase = GetPopularTvShowsUseCase(
                popularTvShowsRepository = PopularTvShowsRepositoryImpl(
                    popularTvShowsRemoteDataSource = PopularTvShowsRemoteDataSource(get())
                )
            )
        )
    }
}