package com.bhdev1215.movieinfo3.data.remote.response

import com.bhdev1215.movieinfo3.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)

