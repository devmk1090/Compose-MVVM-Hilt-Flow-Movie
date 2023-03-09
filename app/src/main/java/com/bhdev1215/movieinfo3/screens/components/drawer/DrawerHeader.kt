package com.bhdev1215.movieinfo3.screens.components.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bhdev1215.movieinfo3.R

@Composable
fun DrawerHeader() {
    Image(
        painter = painterResource(R.drawable.ic_poster_placeholder),
        contentDescription = "Person",
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .size(size = 100.dp),
        colorFilter = ColorFilter.tint(color = Color.White)
    )

    Text(
        text = "안드로이드 개발",
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 16.dp, bottom = 16.dp)
    )

    Divider(
        color = Color.White,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
    )
}