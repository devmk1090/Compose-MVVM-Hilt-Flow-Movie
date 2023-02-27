package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun NavigationDrawer(
    itemClick: (String) -> Unit
) {
    Text(text = "this is drawer", fontSize = 32.sp)
}