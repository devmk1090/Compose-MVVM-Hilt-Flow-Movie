package com.devkproject.movieinfo3.model.artist

import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: ArrayList<Cast>,
    @SerializedName("crew")
    val crew: ArrayList<Crew>,
)
