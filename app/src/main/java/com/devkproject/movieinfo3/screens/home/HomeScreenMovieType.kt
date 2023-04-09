package com.devkproject.movieinfo3.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.devkproject.movieinfo3.model.Movie
import com.devkproject.movieinfo3.navigation.NavigationObject
import com.devkproject.movieinfo3.screens.components.MovieItem
import com.devkproject.movieinfo3.ui.theme.quicksand
import com.devkproject.movieinfo3.util.Constants

@Composable
fun HomeScreenMovieType(
    navController: NavController,
    pagingItems: LazyPagingItems<Movie>,
    title: String,
    type: String,
) {
    LazyColumn(modifier = Modifier.height(250.dp)) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, color = Color.White, fontSize = 18.sp, fontFamily = quicksand, fontWeight = FontWeight.SemiBold)
                ClickableText(
                    text = AnnotatedString("더보기"),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = quicksand,
                        fontWeight = FontWeight.Normal
                    ),
                    onClick = {
                        navController.navigate(
                            NavigationObject.MORE_MOVIE.plus(
                                "/$type"
                            ))
                    })
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(215.dp)
            ) {
                LazyRow(content = {
                    items(pagingItems.itemCount) { it ->
                        MovieItem(
                            modifier = Modifier
                                .width(150.dp)
                                .clickable {
                                    navController.navigate(
                                        NavigationObject.Detail.MOVIE_DETAIL.plus(
                                            "/${pagingItems[it]?.id}"
                                        )
                                    )
                                },
                            imageUrl = "${Constants.IMAGE_BASE_URL}/${pagingItems[it]?.posterPath}",
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