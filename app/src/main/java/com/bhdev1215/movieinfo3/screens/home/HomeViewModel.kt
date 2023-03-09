package com.bhdev1215.movieinfo3.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.bhdev1215.movieinfo3.data.remote.response.MovieResponse
import com.bhdev1215.movieinfo3.data.repository.MovieRepository
import com.bhdev1215.movieinfo3.model.Movie
import com.bhdev1215.movieinfo3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MovieRepository,
) : ViewModel() {

    private var _trendingMovieList = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val trendingMovieList: State<Flow<PagingData<Movie>>> = _trendingMovieList

    val searchData: MutableState<Resource<MovieResponse>?> = mutableStateOf(null)

    init {
        getTrendingMovies()
    }

    /**
     * Movie
     */
    private fun getTrendingMovies() {
        viewModelScope.launch {
            _trendingMovieList.value = moviesRepository.getTrendingWeekMovies().cachedIn(viewModelScope)
        }
    }

    fun getSearch(searchKey: String) {
        viewModelScope.launch {
            flowOf(searchKey).debounce(300)
                .filter {
                    it.trim().isEmpty().not()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    moviesRepository.getSearch(it)
                }.collect {
                    if (it is Resource.Success) {
                        it.data
                    }
                    searchData.value = it
                }
        }
    }
}