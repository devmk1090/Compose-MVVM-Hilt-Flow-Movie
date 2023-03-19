package com.bhdev1215.movieinfo3.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bhdev1215.movieinfo3.data.remote.response.TvDetailResponse
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.util.Resource
import timber.log.Timber

@Composable
fun TvDetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val detail = produceState<Resource<TvDetailResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvDetail(id)
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
            }
        } else {
            CircularProgressIndicator()
        }
    }
}