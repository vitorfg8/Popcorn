package com.github.vitorfg8.popcorn.details.data.model.movie


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)