package com.devkproject.movieinfo3.model

import androidx.compose.ui.graphics.painter.Painter

data class NavigationDrawerItem(
    val image: Painter,
    val isSelected: Boolean,
    val title: String
) {
    fun titleFormat(): String {
        return if (title == "home")
            "영화"
        else
            "티비 시리즈"
    }
}