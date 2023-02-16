package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import timber.log.Timber

@Composable
fun CastDetailScreen(
    id: Int,
    navController: NavController
) {
    Timber.d("$id")
}