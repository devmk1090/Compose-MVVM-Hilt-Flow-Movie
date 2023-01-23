package com.bhdev1215.movieinfo3.data.remote

import com.bhdev1215.movieinfo3.BuildConfig.API_KEY
import com.bhdev1215.movieinfo3.data.remote.response.MovieResponse
import com.bhdev1215.movieinfo3.util.Constants.PAGING_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("trending/movie/day")
    suspend fun getTrendingTodayMovies(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): MovieResponse
}