package com.github.vitorfg8.popcorn.home.trends.ui.mapper

import com.github.vitorfg8.popcorn.home.trends.domain.model.Trend
import java.text.DecimalFormat

fun List<Trend>.toUi(): List<com.github.vitorfg8.popcorn.home.trends.ui.model.TrendUi> {
    return this.map {
        com.github.vitorfg8.popcorn.home.trends.ui.model.TrendUi(
            backdropUrl = getBackdropUrl(it.backdropPath),
            id = it.id,
            mediaType = it.mediaType,
            title = it.title,
            voteAverage = getVoteAverage(it.voteAverage)
        )
    }
}

private fun getBackdropUrl(backdropPath: String): String {
    return "https://image.tmdb.org/t/p/original${backdropPath}"
}

private fun getVoteAverage(voteAverage: Double): String {
    val decimalFormat = DecimalFormat("#.#")
    return decimalFormat.format(voteAverage)
}