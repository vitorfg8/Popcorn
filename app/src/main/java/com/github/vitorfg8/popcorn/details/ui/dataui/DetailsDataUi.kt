package com.github.vitorfg8.popcorn.details.ui.dataui


data class DetailsDataUi(
    val genres: List<String?>?,
    val id: Int,
    val posterUrl: String,
    val overview: String,
    val runtime: String?,
    val title: String,
    val voteAverage: String?,
)