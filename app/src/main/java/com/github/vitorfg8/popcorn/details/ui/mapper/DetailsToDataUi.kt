package com.github.vitorfg8.popcorn.details.ui.mapper

import com.github.vitorfg8.popcorn.details.domain.model.Details
import com.github.vitorfg8.popcorn.details.ui.dataui.DetailsDataUi

fun Details.toUi(): DetailsDataUi {
    return DetailsDataUi(
        genres = genres,
        id = id,
        posterUrl = getPosterUrl(posterPath),
        overview = overview,
        runtime = getRuntime(runtime),
        title = title,
        voteAverage = getVoteAverage(voteAverage)
    )
}

private fun getPosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/original/${posterPath}"
}

private fun getRuntime(runtime: Int?): String? {
    return runtime?.let {
        val hours = it / 60
        val minutes = it % 60
        "$hours h $minutes m"
    }
}

private fun getVoteAverage(voteAverage: Double?): String {
    return String.format("%.1f", voteAverage)
}