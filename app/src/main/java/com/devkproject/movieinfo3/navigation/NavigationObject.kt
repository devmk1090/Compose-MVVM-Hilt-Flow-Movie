package com.devkproject.movieinfo3.navigation

object NavigationObject {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val TV = "tv"
    const val MORE_MOVIE = "moreMovie"
    const val MORE_TV = "moreTv"
    const val SEARCH = "search"

    object More {
        const val MORE_MOVIE_TYPE = "type"
        const val MORE_MOVIE_TYPE_PATH = "/{type}"

        const val MORE_TV_TYPE = "type"
        const val MORE_TV_TYPE_PATH = "/{type}"
    }
    object Detail {
        const val MOVIE_DETAIL = "movieDetail"
        const val MOVIE_ITEM = "movieItem"
        const val MOVIE_DETAIL_PATH = "/{movieItem}"

        const val TV_DETAIL = "tvDetail"
        const val TV_ITEM = "tvItem"
        const val TV_DETAIL_PATH = "/{tvItem}"
    }
    object CastDetail {
        const val CAST_DETAIL = "castDetail"
        const val CAST_ID = "castId"
        const val CAST_DETAIL_PATH = "/{castId}"
    }
}