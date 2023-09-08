package com.github.vitorfg8.popcorn.details.domain.mapper

import com.github.vitorfg8.popcorn.details.data.model.movie.MovieDetailsResponse
import com.github.vitorfg8.popcorn.details.data.model.tvshow.TvShowDetailsResponse
import com.github.vitorfg8.popcorn.details.domain.model.Details

fun MovieDetailsResponse.toDomain(): Details {
    return Details(
        genres = this.genres?.map { it?.name },
        id = id,
        posterPath = posterPath.orEmpty(),
        overview = overview.orEmpty(),
        runtime = runtime,
        title = title ?: originalTitle.orEmpty(),
        voteAverage = voteAverage
    )
}

fun TvShowDetailsResponse.toDomain(): Details {
    return Details(
        genres = this.genres?.map { it?.name },
        id = id,
        posterPath = posterPath.orEmpty(),
        overview = overview.orEmpty(),
        runtime = null,
        title = name ?: originalName.orEmpty(),
        voteAverage = voteAverage
    )
}