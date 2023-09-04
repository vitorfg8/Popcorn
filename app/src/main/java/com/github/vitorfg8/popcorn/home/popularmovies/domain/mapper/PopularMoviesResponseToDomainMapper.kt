package com.github.vitorfg8.popcorn.home.popularmovies.domain.mapper

import com.github.vitorfg8.popcorn.home.popularmovies.data.model.PopularMoviesResponse
import com.github.vitorfg8.popcorn.home.popularmovies.domain.model.PopularMovie

fun PopularMoviesResponse.toDomain(): List<PopularMovie> {
    return results.map {
        PopularMovie(
            id = it.id,
            posterPath = it.posterPath.orEmpty(),
            name = it.title ?: it.originalTitle.orEmpty(),
        )
    }
}