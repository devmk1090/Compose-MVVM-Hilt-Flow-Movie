package com.bhdev1215.movieinfo3.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.bhdev1215.movieinfo3.model.Movie
import timber.log.Timber

//, movieList: LazyPagingItems<Movie>?
@Composable
fun MoreScreen(navController: NavController) {
//    Timber.d(movieList.toString())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "this is MoreScreen")
    }
}