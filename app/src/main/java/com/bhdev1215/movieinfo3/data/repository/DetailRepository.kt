package com.bhdev1215.movieinfo3.data.repository

import com.bhdev1215.movieinfo3.data.remote.TMDBApi
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.model.video.Videos
import com.bhdev1215.movieinfo3.util.Resource
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: TMDBApi) {

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetailResponse> {
        val response = try {
            api.getMovieDetail(movieId)
        } catch (e: Exception) {
            return Resource.Error("Error occurred")
        }
        return Resource.Success(response)
    }

    suspend fun getMovieVideos(movieId: Int): Resource<Videos> {
        val response = try {
            api.getMovieVideos(movieId)
        } catch (e: Exception) {
            return Resource.Error("Error occurred")
        }
        return Resource.Success(response)
    }
}