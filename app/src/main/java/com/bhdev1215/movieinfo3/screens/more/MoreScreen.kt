package com.bhdev1215.movieinfo3.screens.more

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bhdev1215.movieinfo3.model.Movie
import com.bhdev1215.movieinfo3.model.TvSeries
import com.bhdev1215.movieinfo3.navigation.NavigationObject
import com.bhdev1215.movieinfo3.screens.BannerAdView
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.MovieItem
import com.bhdev1215.movieinfo3.screens.home.HomeViewModel
import com.bhdev1215.movieinfo3.util.Constants
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoreScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    currentScreen: String,
    type: String,
    ) {

    var title = ""
    var movieTypeList: LazyPagingItems<Movie>? = null
    var tvTypeList: LazyPagingItems<TvSeries>? = null

    if (currentScreen == NavigationObject.HOME) {
        when (type) {
            "Trending" -> {
                title = "금주의 트렌드"
                movieTypeList = viewModel.trendingMovieList.value.collectAsLazyPagingItems()
            }
            "NowPlaying" -> {
                title = "현재 상영작"
                movieTypeList = viewModel.nowPlayingMovieList.value.collectAsLazyPagingItems()
            }
            "Popular" -> {
                title = "인기 영화"
                movieTypeList = viewModel.popularMovieList.value.collectAsLazyPagingItems()
            }
            "TopRated" -> {
                title = "평점 높은순"
                movieTypeList = viewModel.topRatedMovieList.value.collectAsLazyPagingItems()
            }
            "Upcoming" -> {
                title = "개봉 예정작"
                movieTypeList = viewModel.upcomingMovieList.value.collectAsLazyPagingItems()
            }
        }
    } else {
        when (type) {
            "Trending" -> {
                title = "금주의 트렌드"
                tvTypeList = viewModel.trendingTvList.value.collectAsLazyPagingItems()
            }
            "OnAir" -> {
                title = "방영중"
                tvTypeList = viewModel.onAirTvList.value.collectAsLazyPagingItems()
            }
            "Popular" -> {
                title = "인기 시리즈"
                tvTypeList = viewModel.popularTvList.value.collectAsLazyPagingItems()
            }
            "TopRated" -> {
                title = "평점 높은순"
                tvTypeList = viewModel.topRatedTvList.value.collectAsLazyPagingItems()
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            CommonAppBar(
                title = {
                    Text(text = title, color = Color.White, fontSize = 18.sp)
                },
                modifier = Modifier.fillMaxWidth(),
                showBackArrow = true,
                navController = navController,
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            )
        },
        bottomBar = { BannerAdView(isTest = true) }
    ) {
        Column(
            modifier = Modifier.padding(bottom = 50.dp)
            ) {
            if (currentScreen == NavigationObject.HOME) {
                LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
                    items(movieTypeList!!.itemCount) { it ->
                        MovieItem(
                            modifier = Modifier
                                .height(285.dp)
                                .clickable {
                                    navController.navigate(NavigationObject.Detail.MOVIE_DETAIL.plus("/${movieTypeList[it]?.id}"))
                                },
                            imageUrl = "${Constants.IMAGE_BASE_URL}/${movieTypeList[it]?.posterPath}",
                            title = null,
                            release = null,
                            rating = null
                        )
                    }
                })
            } else {
                LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
                    items(tvTypeList!!.itemCount) { it ->
                        MovieItem(
                            modifier = Modifier
                                .height(285.dp)
                                .clickable {
                                    navController.navigate(NavigationObject.Detail.TV_DETAIL.plus("/${tvTypeList[it]?.id}"))
                                },
                            imageUrl = "${Constants.IMAGE_BASE_URL}/${tvTypeList[it]?.posterPath}",
                            title = null,
                            release = null,
                            rating = null
                        )
                    }
                })
            }
        }
    }
}