package com.github.vitorfg8.popcorn.details.cast.ui.mapper

import com.github.vitorfg8.popcorn.details.cast.domain.model.Cast
import com.github.vitorfg8.popcorn.details.cast.ui.dataui.CastDataUi

fun List<Cast>.toUi(): List<CastDataUi> {
    return map {
        CastDataUi(
            character = it.character,
            id = it.id,
            name = it.name,
            profileUrl = getProfileUrl(it.profilePath)
        )
    }
}

private fun getProfileUrl(profilePath: String): String {
    return "https://image.tmdb.org/t/p/w300${profilePath}"
}