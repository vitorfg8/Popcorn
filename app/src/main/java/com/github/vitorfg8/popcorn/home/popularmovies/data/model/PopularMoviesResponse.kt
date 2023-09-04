package com.github.vitorfg8.popcorn.home.popularmovies.data.model


import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)