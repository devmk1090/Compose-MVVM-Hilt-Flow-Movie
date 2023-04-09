package com.devkproject.movieinfo3.data.remote.response

import com.devkproject.movieinfo3.model.TvSeries
import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvSeries>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)