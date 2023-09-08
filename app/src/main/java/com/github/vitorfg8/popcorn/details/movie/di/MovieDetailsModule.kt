package com.github.vitorfg8.popcorn.details.movie.di

import com.github.vitorfg8.popcorn.details.movie.data.repository.MovieDetailsRepositoryImpl
import com.github.vitorfg8.popcorn.details.movie.datasource.MovieDetailsRemoteDataSource
import com.github.vitorfg8.popcorn.details.movie.domain.usecase.GetMovieDetailsUseCase
import com.github.vitorfg8.popcorn.details.movie.ui.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel {
        MovieDetailsViewModel(
            movieDetailsUseCase = GetMovieDetailsUseCase(
                movieDetailsRepository = MovieDetailsRepositoryImpl(
                    movieDetailsRemoteDataSource = MovieDetailsRemoteDataSource(
                        retrofit = get()
                    )
                )
            )
        )
    }
}