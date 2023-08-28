package com.github.vitorfg8.popcorn.home.trends.di

import com.github.vitorfg8.popcorn.home.trends.data.repository.TrendsRepositoryImpl
import com.github.vitorfg8.popcorn.home.trends.datasource.TrendsRemoteDataSource
import com.github.vitorfg8.popcorn.home.trends.domain.usecase.GetTrendsUseCase
import com.github.vitorfg8.popcorn.home.trends.presentation.TrendsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trendsModule = module {
    viewModel {
        TrendsViewModel(
            getTrendsUseCase = GetTrendsUseCase(
                trendsRepository = TrendsRepositoryImpl(
                    trendsRemoteDataSource = TrendsRemoteDataSource(
                        retrofit = get()
                    )
                )
            )
        )
    }
}