package com.bhdev1215.movieinfo3.model.video

data class Videos(
    val id: Int,
    val results: ArrayList<VideoItems>
)

data class VideoItems(
    val id: String,
    val key: String,
    val name: String,
    val type: String
)