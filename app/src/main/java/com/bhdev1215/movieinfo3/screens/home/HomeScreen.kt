package com.bhdev1215.movieinfo3.screens.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.navigation.NavigationObject
import com.bhdev1215.movieinfo3.screens.BannerAdView
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.drawer.NavigationDrawer
import com.bhdev1215.movieinfo3.ui.theme.primaryGray
import com.bhdev1215.movieinfo3.ui.theme.quicksand
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    currentScreen: String,
) {

    val trendingMovieList = viewModel.trendingMovieList.value.collectAsLazyPagingItems()
    val nowPlayingMovieList = viewModel.nowPlayingMovieList.value.collectAsLazyPagingItems()
    val popularMovieList = viewModel.popularMovieList.value.collectAsLazyPagingItems()
    val topRatedMovieList = viewModel.topRatedMovieList.value.collectAsLazyPagingItems()
    val upcomingMovieList = viewModel.upcomingMovieList.value.collectAsLazyPagingItems()

    val trendingTvList = viewModel.trendingTvList.value.collectAsLazyPagingItems()
    val onAirTvList = viewModel.onAirTvList.value.collectAsLazyPagingItems()
    val popularTvList = viewModel.popularTvList.value.collectAsLazyPagingItems()
    val topRatedTvList = viewModel.topRatedTvList.value.collectAsLazyPagingItems()

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var showAlertDialog by remember { mutableStateOf(false) }

    val title = if (currentScreen == "home") "영화" else "티비 시리즈"
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CommonAppBar(
                    title = {
                        Text(text = title, color = Color.White, fontSize = 18.sp)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    showBackArrow = false,
                    navActions = {
                        IconButton(onClick = { navController.navigate(NavigationObject.SEARCH) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null,
                                tint = primaryGray
                            )
                        }
                    },
                    navController = navController,
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState
                )
            },
            scaffoldState = scaffoldState,
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContent = {
                NavigationDrawer(currentScreen = currentScreen) {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(it)
                }
            },
            bottomBar = { BannerAdView(isTest = true) }
        ) {
            if (currentScreen == NavigationObject.HOME) {
                LazyColumn {
                    item {
                        HomeScreenMovieType(navController = navController, pagingItems = trendingMovieList, title = "금주의 트렌드", type = "Trending")
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    item {
                        HomeScreenMovieType(navController = navController, pagingItems = nowPlayingMovieList, title = "현재 상영작", type = "NowPlaying")
                        Spacer(modifier = Modifier.height(12.dp))

                    }
                    item {
                        HomeScreenMovieType(navController = navController, pagingItems = popularMovieList, title = "인기 영화", type = "Popular")
                        Spacer(modifier = Modifier.height(12.dp))

                    }
                    item {
                        HomeScreenMovieType(navController = navController, pagingItems = topRatedMovieList, title = "평점 높은순", type = "TopRated")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    item {
                        HomeScreenMovieType(navController = navController, pagingItems = upcomingMovieList, title = "개봉 예정작", type = "Upcoming")
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            } else {
                LazyColumn {
                    item {
                        HomeScreenTvType(navController = navController, pagingItems = trendingTvList, title = "금주의 트렌드", type = "Trending")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    item {
                        HomeScreenTvType(navController = navController, pagingItems = onAirTvList, title = "방영중", type = "OnAir")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    item {
                        HomeScreenTvType(navController = navController, pagingItems = popularTvList, title = "인기 시리즈", type = "Popular")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    item {
                        HomeScreenTvType(navController = navController, pagingItems = topRatedTvList, title = "평점 높은순", type = "TopRated")
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }

    val activity = (LocalContext.current as? Activity)

    if (showAlertDialog) {
        OnBackDialog(
            onDismissRequest = { showAlertDialog = false },
            onConfirmClick = {
                activity?.finish()
            }
        )
    }

    BackHandler(true) {
        coroutineScope.launch {
            if (scaffoldState.drawerState.isOpen) {
                scaffoldState.drawerState.close()
            } else {
                showAlertDialog = true
            }
        }
    }
}

@Composable
fun OnBackDialog(onConfirmClick: () -> Unit, onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "종료하시겠습니까?", fontSize = 18.sp, color = Color.White, fontFamily = quicksand, fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = "확인을 누르면 앱이 종료됩니다.", fontSize = 16.sp, color = Color.White, fontFamily = quicksand, fontWeight = FontWeight.Normal)

        },
        confirmButton = {
            TextButton(onClick = { onConfirmClick() }) {
                Text(text = "확인", fontSize = 16.sp, color = Color.White, fontFamily = quicksand, fontWeight = FontWeight.Normal)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = "취소", fontSize = 16.sp, color = Color.White, fontFamily = quicksand, fontWeight = FontWeight.Normal)
            }
        }
    )
}