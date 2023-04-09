package com.devkproject.movieinfo3.data.remote.response

import com.devkproject.movieinfo3.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)

