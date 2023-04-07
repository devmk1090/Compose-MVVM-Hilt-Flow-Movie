package com.bhdev1215.movieinfo3.screens.search

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.navigation.NavigationObject
import com.bhdev1215.movieinfo3.screens.BannerAdView
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.screens.components.MovieItem
import com.bhdev1215.movieinfo3.ui.theme.cornerRadius10
import com.bhdev1215.movieinfo3.ui.theme.primaryDark
import com.bhdev1215.movieinfo3.ui.theme.primaryGray
import com.bhdev1215.movieinfo3.ui.theme.quicksand
import com.bhdev1215.movieinfo3.util.Constants
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val searchResult = viewModel.searchResult.value.collectAsLazyPagingItems()

    Column(
        Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CommonAppBar(
                    title = {
                        Text(text = "검색", color = Color.White, fontSize = 18.sp, fontFamily = quicksand, fontWeight = FontWeight.SemiBold)
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
            Column {
                SearchTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(4.dp)
                        .cornerRadius10(),
                    viewModel = viewModel,
                    onSearch = {
                        viewModel.getSearchResult(it)
                        keyboardController?.hide()
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        modifier = Modifier.padding(bottom = 50.dp),
                        cells = GridCells.Fixed(3),
                        content = {
                            items(searchResult.itemCount) { search ->
                                MovieItem(
                                    modifier = Modifier
                                        .height(185.dp)
                                        .clickable {
                                            navController.navigate(
                                                NavigationObject.Detail.MOVIE_DETAIL.plus(
                                                    "/${searchResult[search]?.id}"
                                                )
                                            )
                                        },
                                    imageUrl = "${Constants.IMAGE_BASE_URL}/${searchResult[search]?.posterPath}",
                                    title = searchResult[search]?.title,
                                    release = searchResult[search]?.releaseDate,
                                    rating = searchResult[search]?.voteAverage.toString()
                                )
                            }
                        })

                    searchResult.apply {
                        when (loadState.refresh) {
                            is LoadState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center),
                                    color = primaryDark,
                                )
                            }
                            is LoadState.NotLoading -> {
                                if (searchResult.itemCount == 0) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            modifier = Modifier.size(200.dp),
                                            painter = painterResource(id = R.drawable.ic_baseline_search_off_24),
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                            is LoadState.Error -> {
                                val e = searchResult.loadState.refresh as LoadState.Error
                                Text(
                                    text = when (e.error) {
                                        is HttpException -> {
                                            "HttpException !"
                                        }
                                        is IOException -> {
                                            "Server error"
                                        }
                                        else -> {
                                            "다시 시도해주세요"
                                        }
                                    },
                                    modifier = Modifier
                                        .align(alignment = Alignment.Center),
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    onSearch: (String) -> Unit = {}
) {
    val resetSearchTerm = viewModel.resetSearchTerm.value

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        value = resetSearchTerm,
        onValueChange = {
            viewModel.resetSearchTerm(it)
        },
        placeholder = {
            Text(text = "검색어를 입력하세요", color = primaryGray)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        trailingIcon = {
            IconButton(onClick = { onSearch(resetSearchTerm) }) {
                Icon(imageVector = Icons.Default.Search, tint = primaryGray, contentDescription = null)
            }
        },
        textStyle = TextStyle(color = Color.White, fontFamily = quicksand, fontWeight = FontWeight.SemiBold),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}