package com.bhdev1215.movieinfo3.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bhdev1215.movieinfo3.data.remote.response.TvDetailResponse
import com.bhdev1215.movieinfo3.model.artist.Credit
import com.bhdev1215.movieinfo3.model.video.Videos
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.CommonTvDetail
import com.bhdev1215.movieinfo3.util.Resource

@Composable
fun TvDetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val detail = produceState<Resource<TvDetailResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvDetail(id)
    }.value
    val videos = produceState<Resource<Videos>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvVideos(id)
    }.value
    val credits = produceState<Resource<Credit>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvCredits(id)
    }.value

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Box {
        if (detail is Resource.Success) {
            Column {
                CommonAppBar(
                    title = {
                        Text(text = detail.data?.name.toString(), color = Color.White, fontSize = 18.sp)
                    },
                    modifier = Modifier.fillMaxSize(),
                    showBackArrow = true,
                    navController = navController,
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState
                )
                CommonTvDetail(
                    navController = navController,
                    item = detail,
                    videoList = videos.data?.results,
                    creditList = credits.data?.cast,
                    crewList = credits.data?.crew
                )
            }
        } else {
            Column {
                CommonAppBar(
                    title = {
                        Text(text = "", color = Color.White, fontSize = 18.sp)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    showBackArrow = true,
                    navController = navController,
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState
                )
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "정보가 없습니다", color = Color.White, fontSize = 24.sp)
                }
            }
        }
    }
}