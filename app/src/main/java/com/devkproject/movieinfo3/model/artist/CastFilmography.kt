package com.devkproject.movieinfo3.model.artist

import com.google.gson.annotations.SerializedName

data class CastFilmography(
    val cast: ArrayList<CastFilmographyDetail>
)

data class CastFilmographyDetail(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    @SerializedName("vote_average")
    val rating: Double
)