package com.bhdev1215.movieinfo3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bhdev1215.movieinfo3.data.remote.TMDBApi
import com.bhdev1215.movieinfo3.model.TvSeries
import retrofit2.HttpException
import java.io.IOException

class PopularTvSource(private val api: TMDBApi) : PagingSource<Int, TvSeries>()  {
    override fun getRefreshKey(state: PagingState<Int, TvSeries>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, TvSeries> {
        return try {
            val nextPage = params.key ?: 1
            val popularTvList = api.getPopularTv(nextPage)
            PagingSource.LoadResult.Page(
                data = popularTvList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (popularTvList.results.isEmpty()) null else popularTvList.page + 1
            )
        } catch (exception: IOException) {
            return PagingSource.LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return PagingSource.LoadResult.Error(exception)
        }
    }
}