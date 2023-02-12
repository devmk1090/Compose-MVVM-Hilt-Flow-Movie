package com.bhdev1215.movieinfo3.screens.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.model.video.VideoItems
import com.bhdev1215.movieinfo3.ui.theme.cornerRadius10
import com.bhdev1215.movieinfo3.util.Constants

@Composable
fun VideoScreen(
    item: ArrayList<VideoItems>
) {
    val context = LocalContext.current
    Column {
        Text(
            text = "트레일러",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            items(item, itemContent = { item ->
                Column(
                    modifier = Modifier.padding(0.dp, 5.dp)
                ) {
                    Box(modifier = Modifier
                        .height(160.dp)
                        .width(240.dp)
                        .clickable {
                            val playVideoIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(Constants.getYoutubeVideoPath(item.key))
                            )
                            context.startActivity(playVideoIntent)
                        }
                    ) {
                        Image(
                            painter = rememberImagePainter(Constants.getYoutubeVideoThumbnail(item.key)),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                                .cornerRadius10(),
                            contentScale = ContentScale.FillWidth
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_video_play),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            })
        }
    }
}