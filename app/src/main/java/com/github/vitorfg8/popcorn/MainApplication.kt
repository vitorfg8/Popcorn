package com.github.vitorfg8.popcorn

import android.app.Application
import com.github.vitorfg8.popcorn.details.di.movieDetailsModule
import com.github.vitorfg8.popcorn.home.popularmovies.di.popularMoviesModule
import com.github.vitorfg8.popcorn.home.populartvshows.di.popularTvShowsModule
import com.github.vitorfg8.popcorn.home.trends.di.trendsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(getModules())
        }
    }

    private fun getModules(): List<Module> = listOf(
        appModule,
        trendsModule,
        popularMoviesModule,
        popularTvShowsModule,
        movieDetailsModule
    )
}