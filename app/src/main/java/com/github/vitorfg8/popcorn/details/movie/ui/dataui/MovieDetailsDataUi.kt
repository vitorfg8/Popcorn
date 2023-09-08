package com.github.vitorfg8.popcorn.details.movie.ui.dataui


data class MovieDetailsDataUi(
    val genres: List<String?>?,
    val id: Int,
    val posterUrl: String,
    val overview: String,
    val runtime: String?,
    val title: String,
    val voteAverage: String?,
)