package com.github.vitorfg8.popcorn.home.populartvshows.ui.mapper

import com.github.vitorfg8.popcorn.home.populartvshows.domain.model.PopularTvShow
import com.github.vitorfg8.popcorn.home.populartvshows.ui.dataUi.PopularTvShowDataUi

fun List<PopularTvShow>.toUi(): List<PopularTvShowDataUi> {
    return this.map {
        PopularTvShowDataUi(
            posterUrl = getPosterUrl(it.posterPath),
            id = it.id,
            title = it.name,
        )
    }
}

private fun getPosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/w300${posterPath}"
}