package com.github.vitorfg8.popcorn.home.trends.domain.model

data class Trend(
    val backdropPath: String,
    val id: Int,
    val mediaType: String,
    val name: String,
    val voteAverage: Double,
)
