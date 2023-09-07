package com.github.vitorfg8.popcorn.details.movie.domain.mapper

import com.github.vitorfg8.popcorn.details.movie.data.model.MovieDetailsResponse
import com.github.vitorfg8.popcorn.details.movie.domain.model.MovieDetails

fun MovieDetailsResponse.toDomain(): MovieDetails {
    return MovieDetails(
        genres = this.genres?.map { it?.name },
        id = id,
        posterPath = posterPath.orEmpty(),
        overview = overview.orEmpty(),
        runtime = runtime,
        title = title ?: originalTitle.orEmpty(),
        voteAverage = voteAverage
    )
}