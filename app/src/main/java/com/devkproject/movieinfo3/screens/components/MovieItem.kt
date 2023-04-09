package com.devkproject.movieinfo3.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.devkproject.movieinfo3.ui.theme.quicksand

@Composable
fun MovieItem(modifier: Modifier, imageUrl: String, title: String?, release: String?, rating: String?) {
    Column {
        Card(modifier = modifier.padding(4.dp)) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Movies",
                contentScale = ContentScale.FillWidth
            )
        }
        title?.let { Text(text = it, color = Color.White, fontSize = 14.sp, fontFamily = quicksand, fontWeight = FontWeight.Normal, modifier = Modifier.padding(start = 2.dp, end = 2.dp)) }
        release?.let { Text(text = it, color = Color.White, fontSize = 14.sp, fontFamily = quicksand, fontWeight = FontWeight.Normal,  modifier = Modifier.padding(start = 2.dp, end = 2.dp)) }
        rating?.let { Text(text = it, color = Color.White, fontSize = 14.sp, fontFamily = quicksand, fontWeight = FontWeight.Normal, modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 12.dp)) }
    }
}