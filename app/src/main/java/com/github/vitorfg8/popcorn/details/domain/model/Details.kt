package com.github.vitorfg8.popcorn.details.domain.model


data class Details(
    val genres: List<String?>?,
    val id: Int,
    val posterPath: String,
    val overview: String,
    val runtime: Int?,
    val title: String,
    val voteAverage: Double?,
)