package com.bhdev1215.movieinfo3.screens.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bhdev1215.movieinfo3.model.Movie
import com.bhdev1215.movieinfo3.navigation.NavigationObject
import com.bhdev1215.movieinfo3.screens.components.MovieItem
import com.bhdev1215.movieinfo3.screens.home.HomeViewModel
import com.bhdev1215.movieinfo3.util.Constants
import timber.log.Timber

//, movieList: LazyPagingItems<Movie>?
@Composable
fun MoreScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController) {

    val trendingMovieList = viewModel.trendingMovieList.value.collectAsLazyPagingItems()

    Timber.d("morescreen : $trendingMovieList")
    Column(
        modifier = Modifier.fillMaxSize()
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
            item(content = {
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyRow(content = {
                        items(trendingMovieList) { it ->
                            MovieItem(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(150.dp)
                                    .clickable {  },
                                imageUrl = "${Constants.IMAGE_BASE_UR}/${it?.posterPath}"
                            )
                        }
                    })
                }
            })
        }    }
}