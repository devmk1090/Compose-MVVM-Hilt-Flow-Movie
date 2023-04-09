package com.devkproject.movieinfo3.screens.detail

import androidx.lifecycle.ViewModel
import com.devkproject.movieinfo3.data.remote.response.MovieDetailResponse
import com.devkproject.movieinfo3.data.remote.response.TvDetailResponse
import com.devkproject.movieinfo3.data.repository.DetailRepository
import com.devkproject.movieinfo3.model.artist.CastDetail
import com.devkproject.movieinfo3.model.artist.CastFilmography
import com.devkproject.movieinfo3.model.artist.Credit
import com.devkproject.movieinfo3.model.video.Videos
import com.devkproject.movieinfo3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetailResponse> {
        return repository.getMovieDetail(movieId)
    }

    suspend fun getMovieVideos(movieId: Int): Resource<Videos> {
        return repository.getMovieVideos(movieId)
    }

    suspend fun getMovieCredit(movieId: Int): Resource<Credit> {
        return repository.getMovieCredit(movieId)
    }

    suspend fun getCastDetail(personId: Int): Resource<CastDetail> {
        return repository.getCastDetail(personId)
    }

    suspend fun getCastFilmography(personId: Int): Resource<CastFilmography> {
        return repository.getCastFilmography(personId)
    }

    //Tv

    suspend fun getTvDetail(tvId: Int): Resource<TvDetailResponse> {
        return repository.getTvDetail(tvId)
    }

    suspend fun getTvVideos(tvId: Int): Resource<Videos> {
        return repository.getMovieVideos(tvId)
    }

    suspend fun getTvCredits(tvId: Int): Resource<Credit> {
        return repository.getMovieCredit(tvId)
    }
}