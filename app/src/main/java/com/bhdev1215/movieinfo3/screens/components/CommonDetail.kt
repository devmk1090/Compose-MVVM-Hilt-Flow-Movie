package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.model.video.VideoItems
import com.bhdev1215.movieinfo3.util.Constants
import com.bhdev1215.movieinfo3.util.Resource

@Composable
fun CommonDetail(
    item: Resource<MovieDetailResponse>,
    videoList: ArrayList<VideoItems>
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
        Column(Modifier.padding(start = 4.dp, end = 4.dp)) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = data.title!!,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.13f),
                    text = "개봉일",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.72f),
                    text = data.releaseDate!!,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraLight,
                    color = Color.White
                )
                VoteAverageIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.15f)
                        .padding(bottom = 12.dp),
                    percentage = data.voteAverage!!.toFloat(),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = data.overview!!,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            VideoScreen(videoList)

        }
    }
}