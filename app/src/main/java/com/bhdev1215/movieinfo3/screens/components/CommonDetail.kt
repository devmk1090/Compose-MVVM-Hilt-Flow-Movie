package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.util.Constants
import com.bhdev1215.movieinfo3.util.Resource

@Composable
fun CommonDetail(
    item: Resource<MovieDetailResponse>
) {
    val data = item.data
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberImagePainter(
                data = Constants.IMAGE_BASE_UR + data!!.posterPath,
                builder = {
                    placeholder(R.drawable.ic_github)
                    crossfade(true)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(585.dp),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Poster"
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = data.title!!,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "출시일",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(5.dp))

        DetailReleaseRating(data.releaseDate!!, data.voteAverage!!.toFloat())
    }
}