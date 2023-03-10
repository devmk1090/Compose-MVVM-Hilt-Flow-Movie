package com.bhdev1215.movieinfo3.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhdev1215.movieinfo3.data.remote.response.MovieResponse
import com.bhdev1215.movieinfo3.data.repository.MovieRepository
import com.bhdev1215.movieinfo3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val moviesRepository: MovieRepository
) : ViewModel() {

    val searchData: MutableState<Resource<MovieResponse>?> = mutableStateOf(null)

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