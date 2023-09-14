package com.github.vitorfg8.popcorn.details.cast.di

import com.github.vitorfg8.popcorn.details.cast.data.repository.CastRepositoryImpl
import com.github.vitorfg8.popcorn.details.cast.datasource.MovieCastRemoteDataSource
import com.github.vitorfg8.popcorn.details.cast.datasource.TvShowCastRemoteDataSource
import com.github.vitorfg8.popcorn.details.cast.domain.usecase.GetCastUseCase
import com.github.vitorfg8.popcorn.details.cast.ui.CastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val castModule = module {
    viewModel {
        CastViewModel(
            getCastUseCase = GetCastUseCase(
                castRepository = CastRepositoryImpl(
                    movieCastRemoteDataSource = MovieCastRemoteDataSource(get()),
                    tvShowCastRemoteDataSource = TvShowCastRemoteDataSource(get())
                )
            )
        )
    }
}