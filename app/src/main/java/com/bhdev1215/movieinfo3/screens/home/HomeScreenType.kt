package com.bhdev1215.movieinfo3.screens.home

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.bhdev1215.movieinfo3.model.TvSeries
import com.bhdev1215.movieinfo3.navigation.NavigationObject
import com.bhdev1215.movieinfo3.screens.components.MovieItem
import com.bhdev1215.movieinfo3.util.Constants

@Composable
fun HomeScreenType(
    navController: NavController,
    pagingItems: LazyPagingItems<TvSeries>,
    title: String,
    type: String,
) {
    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, color = Color.White, fontSize = 18.sp)
                ClickableText(
                    text = AnnotatedString("더보기"),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ),
                    onClick = {
                        navController.navigate(
                            NavigationObject.MORE_TV.plus(
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
                                        NavigationObject.Detail.TV_DETAIL.plus(
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