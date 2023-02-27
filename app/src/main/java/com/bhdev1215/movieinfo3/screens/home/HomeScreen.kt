package com.bhdev1215.movieinfo3.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.navigation.NavigationObject
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.MovieItem
import com.bhdev1215.movieinfo3.screens.components.NavigationDrawer
import com.bhdev1215.movieinfo3.ui.theme.primaryGray
import com.bhdev1215.movieinfo3.util.Constants.IMAGE_BASE_URL
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    val trendingMovieList = viewModel.trendingMovieList.value.collectAsLazyPagingItems()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CommonAppBar(
                    title = {
                        Column {
                            //TODO '영화' , 'TV' 텍스트

                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    showBackArrow = false,
                    navActions = {
                        IconButton(onClick = { /*TODO SEARCH*/ }) {
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
                NavigationDrawer {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            }
        ) {
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "금주의 트렌드", color = Color.White, fontSize = 18.sp)
                        ClickableText(
                            text = AnnotatedString("더보기"),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp
                            ),
                            onClick = {
                                navController.navigate(NavigationObject.MORE)
                            })
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(215.dp)
                    ) {
                        LazyRow(content = {
                            items(trendingMovieList) { it ->
                                MovieItem(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .clickable {
                                            navController.navigate(
                                                NavigationObject.Detail.MOVIE_DETAIL.plus(
                                                    "/${it?.id}"
                                                )
                                            )
                                        },
                                    imageUrl = "$IMAGE_BASE_URL/${it?.posterPath}",
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
    }

    BackHandler {
        coroutineScope.launch {
            scaffoldState.drawerState.close()
        }
    }
}