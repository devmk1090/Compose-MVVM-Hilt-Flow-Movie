package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bhdev1215.movieinfo3.model.artist.CastDetail
import com.bhdev1215.movieinfo3.screens.detail.DetailViewModel
import com.bhdev1215.movieinfo3.util.Resource
import timber.log.Timber

@Composable
fun CastDetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val castDetail = produceState<Resource<CastDetail>>(initialValue = Resource.Loading()) {
        value = viewModel.getCastDetail(id)
    }.value
    Timber.d("$castDetail")
}