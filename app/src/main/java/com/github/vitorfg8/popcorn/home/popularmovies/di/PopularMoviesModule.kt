package com.github.vitorfg8.popcorn.home.popularmovies.di

import com.github.vitorfg8.popcorn.home.popularmovies.data.repository.PopularMoviesRepositoryImpl
import com.github.vitorfg8.popcorn.home.popularmovies.datasource.PopularMoviesRemoteDataSource
import com.github.vitorfg8.popcorn.home.popularmovies.domain.usecase.GetPopularMoviesUseCase
import com.github.vitorfg8.popcorn.home.popularmovies.ui.viewmodel.PopularMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val popularMoviesModule = module {
    viewModel {
        PopularMoviesViewModel(
            getPopularMoviesUseCase = GetPopularMoviesUseCase(
                popularMoviesRepository = PopularMoviesRepositoryImpl(
                    popularMoviesRemoteDataSource = PopularMoviesRemoteDataSource(get())
                )
            )
        )
    }
}