package com.devkproject.movieinfo3.data.remote

import com.devkproject.movieinfo3.BuildConfig.API_KEY
import com.devkproject.movieinfo3.data.remote.response.*
import com.devkproject.movieinfo3.model.artist.CastDetail
import com.devkproject.movieinfo3.model.artist.CastFilmography
import com.devkproject.movieinfo3.model.artist.Credit
import com.devkproject.movieinfo3.model.video.Videos
import com.devkproject.movieinfo3.util.Constants.PAGING_INDEX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    // /trending/{media_type}/{time_window}
    // media_type : all, movie, tv, person
    // time : week, day
    @GET("trending/movie/week")
    suspend fun getTrendingWeekMovies(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
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

    @GET("tv/on_the_air")
    suspend fun getOnAirTv(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): TvResponse

    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): TvResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("page") page: Int = PAGING_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): TvResponse


    @GET("tv/{tv_id}")
    suspend fun getTvDetail(
        @Path("tv_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): TvDetailResponse

    @GET("tv/{tv_id}/videos")
    suspend fun getTvVideos(
        @Path("tv_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): Videos

    @GET("tv/{tv_id}/credits")
    suspend fun getTvCredits(
        @Path("tv_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ko"
    ): Credit
}