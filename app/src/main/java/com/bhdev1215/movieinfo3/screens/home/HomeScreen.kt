package com.bhdev1215.movieinfo3.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.navigation.NavigationObject
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.drawer.NavigationDrawer
import com.bhdev1215.movieinfo3.ui.theme.primaryGray
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    currentScreen: String,
) {

    val trendingMovieList = viewModel.trendingMovieList.value.collectAsLazyPagingItems()
    val nowPlayingMovieList = viewModel.nowPlayingMovieList.value.collectAsLazyPagingItems()

    val trendingTvList = viewModel.trendingTvList.value.collectAsLazyPagingItems()
    val onAirTvList = viewModel.onAirTvList.value.collectAsLazyPagingItems()

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
            }
        ) {
            if (currentScreen == NavigationObject.TV) {
                Column {
                    HomeScreenTvType(navController = navController, pagingItems = trendingTvList, title = "금주의 트렌드", type = "Trending")

                    Spacer(modifier = Modifier.height(10.dp))

                    HomeScreenTvType(navController = navController, pagingItems = onAirTvList, title = "방영중", type = "OnAir")
                }
            } else {
                Column {
                    HomeScreenMovieType(navController = navController, pagingItems = trendingMovieList, title = "금주의 트렌드", type = "Trending")

                    Spacer(modifier = Modifier.height(12.dp))

                    HomeScreenMovieType(navController = navController, pagingItems = nowPlayingMovieList, title = "현재 상영작", type = "NowPlaying")
                }
            }
        }
    }

    if (showAlertDialog) {
        OnBackDialog(
            onDismissRequest = { showAlertDialog = false },
            onConfirmClick = {
                //TODO finish() or navigateUp()
            }
        )
    }

    BackHandler {
        coroutineScope.launch {
            scaffoldState.drawerState.close()
            if (scaffoldState.drawerState.isOpen) {
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
            Text(text = "종료하시겠습니까?", fontSize = 16.sp, color = Color.White)
        },
        text = {
            Text(text = "확인을 누르면 앱이 종료됩니다.", fontSize = 16.sp, color = Color.White)

        },
        confirmButton = {
            TextButton(onClick = { onConfirmClick() }) {
                Text(text = "확인", fontSize = 16.sp, color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = "취소", fontSize = 16.sp, color = Color.White)
            }
        }
    )
}