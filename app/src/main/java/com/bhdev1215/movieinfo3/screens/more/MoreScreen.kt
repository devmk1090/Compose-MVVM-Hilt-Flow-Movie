package com.bhdev1215.movieinfo3.screens.more

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.MovieItem
import com.bhdev1215.movieinfo3.screens.home.HomeViewModel
import com.bhdev1215.movieinfo3.util.Constants
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoreScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController) {

    val trendingMovieList = viewModel.trendingMovieList.value.collectAsLazyPagingItems()

    Timber.d("morescreen : $trendingMovieList")
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CommonAppBar(
            title = {
                    Text(text = "금주의 트렌드")
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            content = {

        })
        LazyColumn {
            item(content = {
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn(content = {
                        items(trendingMovieList) { it ->
                            MovieItem(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(150.dp)
                                    .clickable { },
                                imageUrl = "${Constants.IMAGE_BASE_UR}/${it?.posterPath}"
                            )
                        }
                    })
                }
            })
        }
    }
}