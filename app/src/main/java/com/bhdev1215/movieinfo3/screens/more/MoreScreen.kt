package com.bhdev1215.movieinfo3.screens.more

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bhdev1215.movieinfo3.navigation.NavigationObject
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.MovieItem
import com.bhdev1215.movieinfo3.screens.home.HomeViewModel
import com.bhdev1215.movieinfo3.util.Constants
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoreScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    val trendingMovieList = viewModel.trendingMovieList.value.collectAsLazyPagingItems()

    Column {
        CommonAppBar(
            title = {
                    Text(text = "금주의 트렌드", color = Color.White, fontSize = 18.sp)
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
            navController = navController
        )
        LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
            items(trendingMovieList.itemCount) { it ->
                MovieItem(
                    modifier = Modifier
                        .height(285.dp)
                        .clickable {
                            navController.navigate(NavigationObject.Detail.MOVIE_DETAIL.plus("/${trendingMovieList[it]?.id}"))
                        },
                    imageUrl = "${Constants.IMAGE_BASE_URL}/${trendingMovieList[it]?.posterPath}"
                )
            }
        })
    }
}