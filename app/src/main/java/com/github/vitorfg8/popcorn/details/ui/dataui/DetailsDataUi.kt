package com.github.vitorfg8.popcorn.details.ui.dataui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsDataUi(
    val genres: List<String?>?,
    val id: Int,
    val mediaType: String,
    val posterUrl: String,
    val overview: String,
    val runtime: String?,
    val title: String,
    val voteAverage: String?,
) : Parcelable