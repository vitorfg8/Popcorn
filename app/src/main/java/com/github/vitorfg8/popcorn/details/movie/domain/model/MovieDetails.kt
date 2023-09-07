package com.github.vitorfg8.popcorn.details.movie.domain.model


data class MovieDetails(
    val genres: List<String?>?,
    val id: Int,
    val posterPath: String,
    val overview: String,
    val runtime: Int?,
    val title: String,
    val voteAverage: Double?,
)