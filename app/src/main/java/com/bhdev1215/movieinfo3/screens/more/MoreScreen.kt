package com.bhdev1215.movieinfo3.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.bhdev1215.movieinfo3.model.Movie
import timber.log.Timber

@Composable
fun MoreScreen(movieList: LazyPagingItems<Movie>) {
    Timber.d(movieList.toString())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}