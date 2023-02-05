package com.bhdev1215.movieinfo3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bhdev1215.movieinfo3.data.remote.TMDBApi
import com.bhdev1215.movieinfo3.model.Movie
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class TrendingMovieSource(private val api: TMDBApi): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val trendingMovieList = api.getTrendingWeekMovies(nextPage)
            Timber.d("list: ${trendingMovieList.searches}")
            LoadResult.Page(
                data = trendingMovieList.searches,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = if (trendingMovieList.searches.isEmpty()) null else trendingMovieList.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}