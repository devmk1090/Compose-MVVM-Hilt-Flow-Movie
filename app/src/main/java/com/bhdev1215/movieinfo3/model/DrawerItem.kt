package com.bhdev1215.movieinfo3.model

import androidx.compose.ui.graphics.painter.Painter

data class NavigationDrawerItem(
    val image: Painter,
    val isSelected: Boolean,
    val title: String
)