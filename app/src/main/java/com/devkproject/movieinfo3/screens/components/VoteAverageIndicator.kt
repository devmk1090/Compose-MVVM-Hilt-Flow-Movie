package com.devkproject.movieinfo3.screens.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devkproject.movieinfo3.ui.theme.primaryPink
import com.devkproject.movieinfo3.ui.theme.quicksand

@Composable
fun VoteAverageIndicator(
    modifier: Modifier = Modifier,
    percentage: Float,
) {
    var animation by remember {
        mutableStateOf(false)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (animation) percentage else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 0
        )
    )

    LaunchedEffect(key1 = true) {
        animation = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(20.dp * 2f)
    ) {
        Canvas(
            modifier = Modifier
                .size(20.dp * 2f)
        ) {
            drawArc(
                color = primaryPink,
                startAngle = -90f,
                sweepAngle = (360 * (currentPercentage.value * 0.1)).toFloat(),
                useCenter = false,
                style = Stroke(3.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = String.format("%.1f", percentage),
            fontSize = 18.sp,
            fontFamily = quicksand,
            fontWeight = FontWeight.Bold,
            color = primaryPink
        )
    }
}