package com.github.vitorfg8.popcorn.details.cast.domain.mapper

import com.github.vitorfg8.popcorn.details.cast.data.model.movie.MovieCreditsResponse
import com.github.vitorfg8.popcorn.details.cast.data.model.tvshow.TvShowCreditsResponse
import com.github.vitorfg8.popcorn.details.cast.domain.model.Cast

fun MovieCreditsResponse.toDomain(): List<Cast>? {
    return cast?.map {
        Cast(
            character = it.character.orEmpty(),
            id = it.id,
            name = it.name ?: it.originalName.orEmpty(),
            profilePath = it.profilePath.orEmpty()
        )
    }
}

fun TvShowCreditsResponse.toDomain(): List<Cast>? {
    return cast?.map {
        Cast(
            character = it.character.orEmpty(),
            id = it.id,
            name = it.name ?: it.originalName.orEmpty(),
            profilePath = it.profilePath.orEmpty()
        )
    }
}