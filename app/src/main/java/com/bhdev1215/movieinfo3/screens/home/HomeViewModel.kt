package com.bhdev1215.movieinfo3.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.bhdev1215.movieinfo3.data.repository.MovieRepository
import com.bhdev1215.movieinfo3.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MovieRepository,
) : ViewModel() {

    private var _trendingMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val trendingMovieList: State<Flow<PagingData<Movie>>> = _trendingMovieList

    init {
        getTrendingMovies()
    }

    /**
     * Movie
     */
    fun getTrendingMovies() {
        viewModelScope.launch {
            _trendingMovieList.value = moviesRepository.getTrendingTodayMovies().cachedIn(viewModelScope)
        }
    }
}