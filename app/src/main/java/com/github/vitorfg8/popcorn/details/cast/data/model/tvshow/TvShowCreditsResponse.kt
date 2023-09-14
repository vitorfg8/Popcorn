package com.github.vitorfg8.popcorn.details.cast.data.model.tvshow


import com.google.gson.annotations.SerializedName

data class TvShowCreditsResponse(
    @SerializedName("cast")
    val cast: List<Cast>?,
    @SerializedName("crew")
    val crew: List<Crew?>?,
    @SerializedName("id")
    val id: Int?
)