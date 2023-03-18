package com.bhdev1215.movieinfo3.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bhdev1215.movieinfo3.data.remote.response.MovieResponse
import com.bhdev1215.movieinfo3.data.repository.MovieRepository
import com.bhdev1215.movieinfo3.data.repository.TvRepository
import com.bhdev1215.movieinfo3.model.Movie
import com.bhdev1215.movieinfo3.model.TvSeries
import com.bhdev1215.movieinfo3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    init {
        getTrendingMovies()
        getTrendingTv()
    }

    private var _trendingMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val trendingMovieList: State<Flow<PagingData<Movie>>> = _trendingMovieList

    /**
     * Movie
     */
    private fun getTrendingMovies() {
        viewModelScope.launch {
            _trendingMovieList.value = moviesRepository.getTrendingWeekMovies().cachedIn(viewModelScope)
        }
    }


    /**
     * Tv
     */

    private val _trendingTvSeries = mutableStateOf<Flow<PagingData<TvSeries>>>(emptyFlow())
    val trendingTvSeries: State<Flow<PagingData<TvSeries>>> = _trendingTvSeries

    private fun getTrendingTv() {
        viewModelScope.launch {
            _trendingTvSeries.value = tvRepository.getTrendingWeekTv().cachedIn(viewModelScope)
        }
    }
}