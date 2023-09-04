package com.github.vitorfg8.popcorn.home.trends.ui.mapper

import com.github.vitorfg8.popcorn.home.trends.domain.model.Trend
import com.github.vitorfg8.popcorn.home.trends.ui.dataUi.TrendDataUi
import java.text.DecimalFormat

fun List<Trend>.toUi(): List<TrendDataUi> {
    return this.map {
        TrendDataUi(
            backdropUrl = getBackdropUrl(it.backdropPath),
            id = it.id,
            mediaType = it.mediaType,
            title = it.name,
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