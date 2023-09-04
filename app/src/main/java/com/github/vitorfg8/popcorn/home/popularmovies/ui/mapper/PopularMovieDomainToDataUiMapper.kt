package com.github.vitorfg8.popcorn.home.popularmovies.ui.mapper

import com.github.vitorfg8.popcorn.home.popularmovies.domain.model.PopularMovie
import com.github.vitorfg8.popcorn.home.popularmovies.ui.dataUi.PopularMovieDataUi

fun List<PopularMovie>.toUi(): List<PopularMovieDataUi> {
    return this.map {
        PopularMovieDataUi(
            posterUrl = getPosterUrl(it.posterPath),
            id = it.id,
            title = it.title,
        )
    }
}

private fun getPosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/w300${posterPath}"
}