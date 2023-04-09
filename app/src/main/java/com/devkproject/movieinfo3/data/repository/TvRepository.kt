package com.devkproject.movieinfo3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devkproject.movieinfo3.data.paging.OnAirTvSource
import com.devkproject.movieinfo3.data.paging.PopularTvSource
import com.devkproject.movieinfo3.data.paging.TopRatedTvSource
import com.devkproject.movieinfo3.data.paging.TrendingTvSource
import com.devkproject.movieinfo3.data.remote.TMDBApi
import com.devkproject.movieinfo3.model.TvSeries
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvRepository @Inject constructor(private val api: TMDBApi) {

    fun getTrendingWeekTv(): Flow<PagingData<TvSeries>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TrendingTvSource(api)
            }
        ).flow
    }

    fun getOnAirTv(): Flow<PagingData<TvSeries>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                OnAirTvSource(api)
            }
        ).flow
    }

    fun getPopularTv(): Flow<PagingData<TvSeries>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PopularTvSource(api)
            }
        ).flow
    }

    fun getTopRatedTv(): Flow<PagingData<TvSeries>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TopRatedTvSource(api)
            }
        ).flow
    }
}