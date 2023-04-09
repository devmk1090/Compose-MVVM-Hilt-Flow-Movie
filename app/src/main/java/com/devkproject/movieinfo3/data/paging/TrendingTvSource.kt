package com.devkproject.movieinfo3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devkproject.movieinfo3.data.remote.TMDBApi
import com.devkproject.movieinfo3.model.TvSeries
import retrofit2.HttpException
import java.io.IOException

class TrendingTvSource(private val api: TMDBApi) : PagingSource<Int, TvSeries>() {
    override fun getRefreshKey(state: PagingState<Int, TvSeries>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSeries> {
        return try {
            val nextPage = params.key ?: 1
            val trendingTvList = api.getTrendingWeekTv(nextPage)
            LoadResult.Page(
                data = trendingTvList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (trendingTvList.results.isEmpty()) null else trendingTvList.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}