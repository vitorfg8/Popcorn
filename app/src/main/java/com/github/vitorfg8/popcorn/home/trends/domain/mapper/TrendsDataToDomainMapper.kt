package com.github.vitorfg8.popcorn.home.trends.domain.mapper

import com.github.vitorfg8.popcorn.home.trends.data.model.TrendsResponse
import com.github.vitorfg8.popcorn.home.trends.domain.model.Trend

fun TrendsResponse.toDomain(): List<Trend> {
    return this.results.map {
        Trend(
            backdropPath = it.backdropPath.orEmpty(),
            id = it.id,
            mediaType = it.mediaType.orEmpty(),
            name = it.name ?: it.originalName.orEmpty(),
            voteAverage = it.voteAverage ?: 0.0
        )
    }
}