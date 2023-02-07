package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailReleaseRating(
    releaseDate: String,
    rating: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.83f),
            text = releaseDate,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraLight,
            color = Color.White
        )
        VoteAverageIndicator(
            modifier = Modifier
                .fillMaxWidth(0.17f),
            percentage = rating
        )
    }
}