package com.bhdev1215.movieinfo3.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.model.video.Videos
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.CommonDetail
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
    val videos = produceState<Resource<Videos>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieVideos(id)
    }.value

    Box {
        if (detail is Resource.Success && videos is Resource.Success) {
            Column {
                CommonAppBar(
                    title = {
                        Text(text = detail.data?.title.toString(), color = Color.White, fontSize = 18.sp)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    showBackArrow = true,
                    navController = navController
                )

                //Detail
                CommonDetail(item = detail, videoList = videos.data!!.results)
            }
        } else {
            CircularProgressIndicator()
        }
    }
}