package com.bhdev1215.movieinfo3.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bhdev1215.movieinfo3.data.repository.MovieRepository
import com.bhdev1215.movieinfo3.data.repository.TvRepository
import com.bhdev1215.movieinfo3.model.Movie
import com.bhdev1215.movieinfo3.model.TvSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    private var _trendingMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val trendingMovieList: State<Flow<PagingData<Movie>>> = _trendingMovieList

    private var _nowPlayingMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val nowPlayingMovieList: State<Flow<PagingData<Movie>>> = _nowPlayingMovieList

    private val _trendingTvSeries = mutableStateOf<Flow<PagingData<TvSeries>>>(emptyFlow())
    val trendingTvSeries: State<Flow<PagingData<TvSeries>>> = _trendingTvSeries

    init {
        getTrendingMovies()
        getNowPlyingMovies()
        getTrendingTv()
    }

    /**
     * Movie
     */

    private fun getTrendingMovies() {
        viewModelScope.launch {
            _trendingMovieList.value = moviesRepository.getTrendingWeekMovies().cachedIn(viewModelScope)
        }
    }

    private fun getNowPlyingMovies() {
        viewModelScope.launch {
            _nowPlayingMovieList.value = moviesRepository.getNowPlayingMovies().cachedIn(viewModelScope)
        }
    }

    /**
     * Tv
     */

    private fun getTrendingTv() {
        viewModelScope.launch {
            _trendingTvSeries.value = tvRepository.getTrendingWeekTv().cachedIn(viewModelScope)
        }
    }
}