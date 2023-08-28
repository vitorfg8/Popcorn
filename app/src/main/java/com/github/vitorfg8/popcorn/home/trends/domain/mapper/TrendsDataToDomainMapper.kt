package com.github.vitorfg8.popcorn.home.trends.domain.mapper

import com.github.vitorfg8.popcorn.home.trends.data.model.TrendsResponse
import com.github.vitorfg8.popcorn.home.trends.domain.model.Trend

fun TrendsResponse.toDomain(): List<Trend> {
    return this.results.map {
        Trend(
            backdropPath = it.backdropPath,
            id = it.id,
            mediaType = it.mediaType,
            title = it.mediaType,
            voteAverage = it.voteAverage
        )
    }
}