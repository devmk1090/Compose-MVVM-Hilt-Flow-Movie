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
import kotlinx.coroutines.flow.flow
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

    suspend fun getSearch(searchKey: String): Flow<Resource<MovieResponse>> = flow {
        emit(Resource.Loading())
        try {
            val search = api.getSearch(searchKey)
            emit(Resource.Success(search))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}