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

    //Movie
    private var _trendingMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val trendingMovieList: State<Flow<PagingData<Movie>>> = _trendingMovieList

    private var _nowPlayingMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val nowPlayingMovieList: State<Flow<PagingData<Movie>>> = _nowPlayingMovieList

    private var _popularMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val popularMovieList: State<Flow<PagingData<Movie>>> = _popularMovieList

    private var _topRatedMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val topRatedMovieList: State<Flow<PagingData<Movie>>> = _topRatedMovieList

    //TV
    private val _trendingTvList = mutableStateOf<Flow<PagingData<TvSeries>>>(emptyFlow())
    val trendingTvList: State<Flow<PagingData<TvSeries>>> = _trendingTvList

    private val _onAirTvList = mutableStateOf<Flow<PagingData<TvSeries>>>(emptyFlow())
    val onAirTvList: State<Flow<PagingData<TvSeries>>> = _onAirTvList

    private val _popularTvList = mutableStateOf<Flow<PagingData<TvSeries>>>(emptyFlow())
    val popularTvList: State<Flow<PagingData<TvSeries>>> = _popularTvList

    private val _topRatedTvList = mutableStateOf<Flow<PagingData<TvSeries>>>(emptyFlow())
    val topRatedTvList: State<Flow<PagingData<TvSeries>>> = _topRatedTvList

    init {
        getTrendingMovies()
        getNowPlyingMovies()
        getPopularMovies()
        getTopRatedTv()

        getTrendingTv()
        getOnAirTv()
        getPopularTv()
        getTopRatedTv()
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

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovieList.value = moviesRepository.getPopularMovies().cachedIn(viewModelScope)
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            _topRatedMovieList.value = moviesRepository.getTopRatedMovies().cachedIn(viewModelScope)
        }
    }

    /**
     * Tv
     */

    private fun getTrendingTv() {
        viewModelScope.launch {
            _trendingTvList.value = tvRepository.getTrendingWeekTv().cachedIn(viewModelScope)
        }
    }

    private fun getOnAirTv() {
        viewModelScope.launch {
            _onAirTvList.value = tvRepository.getOnAirTv().cachedIn(viewModelScope)
        }
    }

    private fun getPopularTv() {
        viewModelScope.launch {
            _popularTvList.value = tvRepository.getPopularTv().cachedIn(viewModelScope)
        }
    }

    private fun getTopRatedTv() {
        viewModelScope.launch {
            _topRatedTvList.value = tvRepository.getTopRatedTv().cachedIn(viewModelScope)
        }
    }
}