package com.devkproject.movieinfo3.data.repository

import com.devkproject.movieinfo3.data.remote.TMDBApi
import com.devkproject.movieinfo3.data.remote.response.MovieDetailResponse
import com.devkproject.movieinfo3.data.remote.response.TvDetailResponse
import com.devkproject.movieinfo3.model.artist.CastDetail
import com.devkproject.movieinfo3.model.artist.CastFilmography
import com.devkproject.movieinfo3.model.artist.Credit
import com.devkproject.movieinfo3.model.video.Videos
import com.devkproject.movieinfo3.util.Resource
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: TMDBApi) {

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetailResponse> {
        val response = try {
            api.getMovieDetail(movieId)
        } catch (e: Exception) {
            return Resource.Error("데이터를 가져오는데 실패했습니다.")
        }
        return Resource.Success(response)
    }

    suspend fun getMovieVideos(movieId: Int): Resource<Videos> {
        val response = try {
            api.getMovieVideos(movieId)
        } catch (e: Exception) {
            return Resource.Error("데이터를 가져오는데 실패했습니다.")
        }
        return Resource.Success(response)
    }

    suspend fun getMovieCredit(movieId: Int): Resource<Credit> {
        val response = try {
            api.getMovieCredit(movieId)
        } catch (e: Exception) {
            return Resource.Error("데이터를 가져오는데 실패했습니다.")
        }
        return Resource.Success(response)
    }

    suspend fun getCastDetail(personId: Int): Resource<CastDetail> {
        val response = try {
            api.getCastDetail(personId)
        } catch (e: Exception) {
            return Resource.Error("데이터를 가져오는데 실패했습니다.")
        }
        return Resource.Success(response)
    }

    suspend fun getCastFilmography(personId: Int): Resource<CastFilmography> {
        val response = try {

            api.getCastFilmography(personId)
        } catch (e: Exception) {
            return Resource.Error("Error occurred")
        }
        return Resource.Success(response)
    }

    //Tv
    suspend fun getTvDetail(tvId: Int): Resource<TvDetailResponse> {
        val response = try {
            api.getTvDetail(tvId)
        } catch (e: Exception) {
            return Resource.Error("데이터를 가져오는데 실패했습니다.")
        }
        return Resource.Success(response)
    }

    suspend fun getTvVideos(tvId: Int): Resource<Videos> {
        val response = try {
            api.getTvVideos(tvId)
        } catch (e: Exception) {
            return Resource.Error("데이터를 가져오는데 실패했습니다.")
        }
        return Resource.Success(response)
    }

    suspend fun getTvCredits(tvId: Int): Resource<Credit> {
        val response = try {
            api.getTvCredits(tvId)
        } catch (e: Exception) {
            return Resource.Error("데이터를 가져오는데 실패했습니다.")
        }
        return Resource.Success(response)
    }

}