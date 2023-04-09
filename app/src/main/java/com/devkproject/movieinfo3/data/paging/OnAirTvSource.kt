package com.devkproject.movieinfo3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devkproject.movieinfo3.data.remote.TMDBApi
import com.devkproject.movieinfo3.model.TvSeries
import retrofit2.HttpException
import java.io.IOException

class OnAirTvSource(private val api: TMDBApi) : PagingSource<Int, TvSeries>()  {
    override fun getRefreshKey(state: PagingState<Int, TvSeries>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, TvSeries> {
        return try {
            val nextPage = params.key ?: 1
            val onAirTvList = api.getOnAirTv(nextPage)
            PagingSource.LoadResult.Page(
                data = onAirTvList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (onAirTvList.results.isEmpty()) null else onAirTvList.page + 1
            )
        } catch (exception: IOException) {
            return PagingSource.LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return PagingSource.LoadResult.Error(exception)
        }
    }
}