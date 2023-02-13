package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bhdev1215.movieinfo3.model.artist.Cast
import com.bhdev1215.movieinfo3.ui.theme.cornerRadius10
import com.bhdev1215.movieinfo3.util.Constants

@Composable
fun CreditScreen(item: ArrayList<Cast>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = "출연진",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(modifier = Modifier.fillMaxHeight()) {
            items(item, itemContent = { item ->
                Column(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 8.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),

                    ) {
                    Image(
                        painter = rememberImagePainter(Constants.IMAGE_BASE_URL.plus(item.profilePath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .height(120.dp)
                            .width(80.dp)
                            .cornerRadius10()
                            .clickable {

                            }

                    )
                    Text(
                        text = item.name,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            })
        }
    }
}