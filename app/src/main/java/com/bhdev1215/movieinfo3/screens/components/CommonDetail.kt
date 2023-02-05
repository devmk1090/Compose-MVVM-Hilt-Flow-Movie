package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.util.Constants
import com.bhdev1215.movieinfo3.util.Resource

@Composable
fun CommonDetail(
    item: Resource<MovieDetailResponse>,
    scrollState: LazyListState,
) {
    val data = item.data
    Column {
        Image(
            painter = rememberImagePainter(
                data = Constants.IMAGE_BASE_UR + data!!.posterPath,
                builder = {
                    placeholder(R.drawable.ic_github)
                    crossfade(true)
                }
            ),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "Poster"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = data.title!!,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}