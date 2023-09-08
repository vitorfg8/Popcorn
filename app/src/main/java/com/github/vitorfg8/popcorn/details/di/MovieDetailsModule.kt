package com.github.vitorfg8.popcorn.details.di

import com.github.vitorfg8.popcorn.details.data.repository.DetailsRepositoryImpl
import com.github.vitorfg8.popcorn.details.datasource.MovieDetailsRemoteDataSource
import com.github.vitorfg8.popcorn.details.datasource.TvShowDetailsRemoteDataSource
import com.github.vitorfg8.popcorn.details.domain.usecase.DetailsUseCase
import com.github.vitorfg8.popcorn.details.ui.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel {
        DetailsViewModel(
            detailsUseCase = DetailsUseCase(
                detailsRepository = DetailsRepositoryImpl(
                    movieDetailsRemoteDataSource = MovieDetailsRemoteDataSource(
                        retrofit = get()
                    ),
                    tvShowDetailsRemoteDataSource = TvShowDetailsRemoteDataSource(
                        retrofit = get()
                    )
                )
            )
        )
    }
}