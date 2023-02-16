package com.bhdev1215.movieinfo3.navigation

object NavigationObject {
    const val HOME = "home"
    const val MORE = "more"
    const val CAST = "cast"

    object Detail {
        const val MOVIE_DETAIL = "movieDetail"
        const val MOVIE_ITEM = "movieItem"
        const val MOVIE_DETAIL_PATH = "/{movieItem}"
    }
    object CastDetail {
        const val CAST_DETAIL = "castDetail"
        const val CAST_ID = "castId"
        const val CAST_DETAIL_PATH = "/{castId}"
    }
}