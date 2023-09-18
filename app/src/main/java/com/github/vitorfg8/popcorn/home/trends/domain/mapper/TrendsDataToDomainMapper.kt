package com.github.vitorfg8.popcorn.home.trends.domain.mapper

import com.github.vitorfg8.popcorn.home.trends.data.model.Result
import com.github.vitorfg8.popcorn.home.trends.data.model.TrendsResponse
import com.github.vitorfg8.popcorn.home.trends.domain.model.Trend
import com.github.vitorfg8.popcorn.utils.Constants.MOVIE
import com.github.vitorfg8.popcorn.utils.Constants.TV

fun TrendsResponse.toDomain(): List<Trend> {
    return this.results.map {
        Trend(
            backdropPath = it.backdropPath.orEmpty(),
            id = it.id,
            mediaType = it.mediaType.orEmpty(),
            title = getTitle(it),
            voteAverage = it.voteAverage ?: 0.0
        )
    }
}


fun getTitle(result: Result?): String {
    return when (result?.mediaType) {
        MOVIE -> result.title ?: result.originalTitle.orEmpty()
        TV -> result.name ?: result.originalTitle.orEmpty()
        else -> ""
    }
}