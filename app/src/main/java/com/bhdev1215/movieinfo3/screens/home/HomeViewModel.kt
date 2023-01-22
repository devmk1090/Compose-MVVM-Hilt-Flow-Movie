package com.bhdev1215.movieinfo3.screens.home

import androidx.lifecycle.ViewModel
import com.bhdev1215.movieinfo3.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MovieRepository,
) : ViewModel() {


    /**
     * Movie
     */
    fun getTrendingMovies(id: Int?) {

    }
}