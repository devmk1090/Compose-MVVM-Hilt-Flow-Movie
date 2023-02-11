package com.bhdev1215.movieinfo3.util

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val PAGING_INDEX = 0
    const val IMAGE_BASE_UR = "https://image.tmdb.org/t/p/w500/"

    //Youtube
    const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="
    const val YOUTUBE_THUMBNAIL_BASE_URL = "https://img.youtube.com/vi/"
    const val YOUTUBE_THUMBNAIL_URL_JPG = "/0.jpg"

    @JvmStatic
    fun getYoutubeVideoPath(key: String?): String {
        return YOUTUBE_BASE_URL + key
    }

    @JvmStatic
    fun getYoutubeVideoThumbnail(key: String?): String {
        return YOUTUBE_THUMBNAIL_BASE_URL + key + YOUTUBE_THUMBNAIL_URL_JPG
    }
}