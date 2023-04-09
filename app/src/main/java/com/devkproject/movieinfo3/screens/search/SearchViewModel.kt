package com.devkproject.movieinfo3.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.devkproject.movieinfo3.data.repository.SearchRepository
import com.devkproject.movieinfo3.model.Search
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _resetSearchTerm = mutableStateOf("")
    val resetSearchTerm: State<String> = _resetSearchTerm

    fun resetSearchTerm(term: String) {
        _resetSearchTerm.value = term
    }

    private val _searchResult = mutableStateOf<Flow<PagingData<Search>>>(emptyFlow())
    val searchResult: State<Flow<PagingData<Search>>> = _searchResult

    fun getSearchResult(queryText: String) {
        viewModelScope.launch {
            _searchResult.value = searchRepository.getSearch(queryText).map { data ->
                data.filter {
                    it.title != null
                }
            }.cachedIn(viewModelScope)
        }
    }
}