package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.bhdev1215.movieinfo3.data.remote.response.MovieDetailResponse
import com.bhdev1215.movieinfo3.model.artist.Cast
import com.bhdev1215.movieinfo3.model.artist.Crew
import com.bhdev1215.movieinfo3.model.video.VideoItems
import com.bhdev1215.movieinfo3.screens.people.CreditScreen
import com.bhdev1215.movieinfo3.ui.theme.cornerRadius10
import com.bhdev1215.movieinfo3.util.Constants
import com.bhdev1215.movieinfo3.util.Resource

@Composable
fun CommonDetail(
    navController: NavController,
    item: Resource<MovieDetailResponse>,
    videoList: ArrayList<VideoItems>? = null,
    creditList: ArrayList<Cast>? = null,
    crewList: ArrayList<Crew>? = null
) {
    val data = item.data
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 48.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = Constants.IMAGE_BASE_URL + data!!.posterPath,
                builder = {
                    crossfade(true)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(585.dp)
                .cornerRadius10(),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Poster"
        )
        Column(Modifier.padding(start = 4.dp, end = 4.dp)) {
            Spacer(modifier = Modifier.height(10.dp))

            VideoScreen(videoList)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = data.title!!,
                color = Color.White,
                fontSize = 24.sp,
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "장르",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                LazyRow {
                    items(data.genres!!.size) { it ->
                        Text(
                            modifier = Modifier
                                .padding(start = 4.dp),
                            text = data.genres[it].name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraLight,
                            color = Color.White
                        )
                    }
                }
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

            CreditScreen(navController, creditList, crewList)
        }
    }
}