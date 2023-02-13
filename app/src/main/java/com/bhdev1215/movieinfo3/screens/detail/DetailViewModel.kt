package com.bhdev1215.movieinfo3.screens.detail

import androidx.lifecycle.ViewModel
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.data.repository.DetailRepository
import com.bhdev1215.movieinfo3.model.artist.Credit
import com.bhdev1215.movieinfo3.model.video.Videos
import com.bhdev1215.movieinfo3.util.Resource
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
}