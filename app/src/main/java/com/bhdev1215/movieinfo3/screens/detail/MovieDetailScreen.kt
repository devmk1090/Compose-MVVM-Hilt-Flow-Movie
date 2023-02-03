package com.bhdev1215.movieinfo3.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.model.Movie
import com.bhdev1215.movieinfo3.util.Resource

@Composable
fun MovieDetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val detail = produceState<Resource<MovieDetailResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieDetail(id)
    }.value


    Box {
        Text(text = "Detail Screen : ${detail.data?.id}")
    }
}