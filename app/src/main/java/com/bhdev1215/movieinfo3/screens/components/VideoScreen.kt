package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.runtime.Composable
import com.bhdev1215.movieinfo3.model.video.Videos
import com.bhdev1215.movieinfo3.util.Resource
import timber.log.Timber

@Composable
fun VideoScreen(
    item: Resource<Videos>
) {
    Timber.d("501501", item)
}