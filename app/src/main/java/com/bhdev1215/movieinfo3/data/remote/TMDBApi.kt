package com.bhdev1215.movieinfo3.data.remote

import com.bhdev1215.movieinfo3.BuildConfig.API_KEY
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.data.remote.response.MovieResponse
import com.bhdev1215.movieinfo3.data.remote.response.SearchResponse
import com.bhdev1215.movieinfo3.data.remote.response.TvResponse
import com.bhdev1215.movieinfo3.model.artist.CastDetail
import com.bhdev1215.movieinfo3.model.artist.CastFilmography
import com.bhdev1215.movieinfo3.model.artist.Credit
import com.bhdev1215.movieinfo3.model.video.Videos
import com.bhdev1215.movieinfo3.util.Constants.PAGING_INDEX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    // /trending/{media_type}/{time_window}
    // media_type : all, movie, tv, person
    @GET("trending/movie/week")
    suspend fun getTrendingWeekMovies(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): MovieDetailResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): Videos

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredit(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): Credit

    @GET("person/{person_id}")
    suspend fun getCastDetail(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): CastDetail

    @GET("person/{person_id}/movie_credits")
    suspend fun getCastFilmography(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): CastFilmography

    @GET("search/multi")
    suspend fun getMultiSearch(
        @Query("query") searchQuery: String,
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): SearchResponse


    //TV

    @GET("trending/tv/week")
    suspend fun getTrendingWeekTv(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): TvResponse
}