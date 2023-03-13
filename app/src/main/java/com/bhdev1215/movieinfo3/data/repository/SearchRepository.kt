package com.bhdev1215.movieinfo3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bhdev1215.movieinfo3.data.paging.SearchPagingSource
import com.bhdev1215.movieinfo3.data.remote.TMDBApi
import com.bhdev1215.movieinfo3.model.Search
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: TMDBApi) {
    fun getSearch(queryText: String): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                SearchPagingSource(api, queryText)
            }
        ).flow
    }}