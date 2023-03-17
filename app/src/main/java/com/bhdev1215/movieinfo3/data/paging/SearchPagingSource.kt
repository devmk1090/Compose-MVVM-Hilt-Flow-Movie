package com.bhdev1215.movieinfo3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bhdev1215.movieinfo3.data.remote.TMDBApi
import com.bhdev1215.movieinfo3.model.Search
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(private val api: TMDBApi, private val queryText: String)
    : PagingSource<Int, Search>() {
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try {
            val nextPage = params.key ?: 1
            val searchResult = api.getMultiSearch(queryText, nextPage)
            LoadResult.Page(
                data = searchResult.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (searchResult.searches.isEmpty()) null else searchResult.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}