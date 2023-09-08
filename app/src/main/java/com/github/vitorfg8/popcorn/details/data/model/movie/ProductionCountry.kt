package com.github.vitorfg8.popcorn.details.data.model.movie


import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("name")
    val name: String?
)