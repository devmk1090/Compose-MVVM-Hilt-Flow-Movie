package com.devkproject.movieinfo3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devkproject.movieinfo3.data.remote.TMDBApi
import com.devkproject.movieinfo3.model.Movie
import retrofit2.HttpException
import java.io.IOException

class NowPlayingMovieSource(private val api: TMDBApi): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val nowPlayingMovie = api.getNowPlayingMovie(nextPage)
            LoadResult.Page(
                data = nowPlayingMovie.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nowPlayingMovie.searches.isEmpty()) null else nowPlayingMovie.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}