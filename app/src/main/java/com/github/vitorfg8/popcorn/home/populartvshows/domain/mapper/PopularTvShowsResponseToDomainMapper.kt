package com.github.vitorfg8.popcorn.home.populartvshows.domain.mapper

import com.github.vitorfg8.popcorn.home.populartvshows.data.model.PopularTvShowsResponse
import com.github.vitorfg8.popcorn.home.populartvshows.domain.model.PopularTvShow

fun PopularTvShowsResponse.toDomain(): List<PopularTvShow> {
    return results.map {
        PopularTvShow(
            id = it.id,
            posterPath = it.posterPath.orEmpty(),
            name = it.name ?: it.originalName.orEmpty(),
        )
    }
}