package com.bhdev1215.movieinfo3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bhdev1215.movieinfo3.data.paging.TrendingMovieSource
import com.bhdev1215.movieinfo3.data.remote.TMDBApi
import com.bhdev1215.movieinfo3.data.remote.response.MovieResponse
import com.bhdev1215.movieinfo3.model.Movie
import com.bhdev1215.movieinfo3.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: TMDBApi) {

    fun getTrendingWeekMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TrendingMovieSource(api)
            }
        ).flow
    }

    suspend fun getSearch(searchKey: String): Resource<MovieResponse> {
        val response = try {
            api.getSearch(searchKey)
        } catch (e: Exception) {
            return Resource.Error("Error occurred")
        }
        return Resource.Success(response)
    }
}