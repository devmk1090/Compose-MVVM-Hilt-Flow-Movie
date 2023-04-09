package com.devkproject.movieinfo3.screens.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.devkproject.movieinfo3.R
import com.devkproject.movieinfo3.model.video.VideoItems
import com.devkproject.movieinfo3.ui.theme.cornerRadius10
import com.devkproject.movieinfo3.util.Constants

@Composable
fun VideoScreen(
    item: ArrayList<VideoItems>? = null
) {
    val context = LocalContext.current
    if (item != null) {
        Column {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(item, itemContent = { item ->
                    Box(modifier = Modifier
                        .height(135.dp)
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
                })
            }
        }
    }
}