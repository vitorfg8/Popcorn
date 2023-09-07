package com.github.vitorfg8.popcorn.details.movie.ui.mapper

import com.github.vitorfg8.popcorn.details.movie.domain.model.MovieDetails
import com.github.vitorfg8.popcorn.details.movie.ui.dataui.MovieDetailsDataUi


fun MovieDetails.toUi(): MovieDetailsDataUi {
    return MovieDetailsDataUi(
        genres = genres,
        id = id,
        posterUrl = getPosterUrl(posterPath),
        overview = overview,
        runtime = runtime,
        title = title,
        voteAverage = voteAverage
    )
}

private fun getPosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/w300${posterPath}"
}