package com.github.vitorfg8.popcorn.details.movie.ui.mapper

import com.github.vitorfg8.popcorn.details.movie.domain.model.MovieDetails
import com.github.vitorfg8.popcorn.details.movie.ui.dataui.MovieDetailsDataUi
import java.text.DecimalFormat


fun MovieDetails.toUi(): MovieDetailsDataUi {
    return MovieDetailsDataUi(
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
    return "https://image.tmdb.org/t/p/w300${posterPath}"
}

private fun getRuntime(runtime: Int?): String? {
    return runtime?.let {
        val hours = it / 60
        val minutes = it % 60
        "$hours h $minutes m"
    }
}

private fun getVoteAverage(voteAverage: Double?): String {
    val decimalFormat = DecimalFormat("#.#")
    return decimalFormat.format(voteAverage)
}