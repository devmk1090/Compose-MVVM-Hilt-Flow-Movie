package com.devkproject.movieinfo3.screens.people

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.devkproject.movieinfo3.R
import com.devkproject.movieinfo3.model.artist.CastDetail
import com.devkproject.movieinfo3.model.artist.CastFilmography
import com.devkproject.movieinfo3.navigation.NavigationObject
import com.devkproject.movieinfo3.screens.components.CommonAppBar
import com.devkproject.movieinfo3.screens.components.MovieItem
import com.devkproject.movieinfo3.screens.detail.DetailViewModel
import com.devkproject.movieinfo3.ui.theme.cornerRadius10
import com.devkproject.movieinfo3.ui.theme.quicksand
import com.devkproject.movieinfo3.util.Constants
import com.devkproject.movieinfo3.util.Resource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CastDetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val castDetail = produceState<Resource<CastDetail>>(initialValue = Resource.Loading()) {
        value = viewModel.getCastDetail(id)
    }.value

    val castFilmography = produceState<Resource<CastFilmography>>(initialValue = Resource.Loading()) {
        value = viewModel.getCastFilmography(id)
    }.value

    val data = castDetail.data
    val castFilmographyList = castFilmography.data?.cast
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Box {
        if (castDetail is Resource.Success && castFilmography is Resource.Success) {
            Column {
                CommonAppBar(
                    title = {
                        Text(text = data!!.name, color = Color.White, fontSize = 18.sp)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    showBackArrow = true,
                    navController = navController,
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState
                )
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Row {
                        Image(
                            painter = rememberImagePainter(
                                data = Constants.IMAGE_BASE_URL + data!!.profilePath,
                                builder = {
                                    placeholder(R.drawable.ic_person_placeholder)
                                    error(R.drawable.ic_person_placeholder)
                                }
                            ),
                            modifier = Modifier
                                .width(200.dp)
                                .height(300.dp)
                                .cornerRadius10(),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = "Profile"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = data.name, color = Color.White, fontSize = 16.sp, fontFamily = quicksand, fontWeight = FontWeight.Normal)
                            Spacer(modifier = Modifier.height(10.dp))

                            data.birthday?.let { Text(text = it, color = Color.White, fontSize = 16.sp, fontFamily = quicksand, fontWeight = FontWeight.Normal) }
                            data.deathday?.let { Text(text = "-\n$it", color = Color.White, fontSize = 16.sp, fontFamily = quicksand, fontWeight = FontWeight.Normal) }
                            Spacer(modifier = Modifier.height(10.dp))

                            data.knownForDepartment?.let { Text(text = it, color = Color.White, fontSize = 16.sp, fontFamily = quicksand, fontWeight = FontWeight.Normal) }
                            Spacer(modifier = Modifier.height(10.dp))

                            data.placeOfBirth?.let { Text(text = it, color = Color.White, fontSize = 16.sp, fontFamily = quicksand, fontWeight = FontWeight.Normal)}
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "필모그래피",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = quicksand,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyVerticalGrid(cells = GridCells.Fixed(3), content = {
                    items(castFilmographyList!!.size) { it ->
                        MovieItem(
                            modifier = Modifier
                                .height(185.dp)
                                .clickable {
                                    navController.navigate(
                                        NavigationObject.Detail.MOVIE_DETAIL.plus(
                                            "/${castFilmographyList[it].id}"
                                        )
                                    )
                                },
                            imageUrl = "${Constants.IMAGE_BASE_URL}/${castFilmographyList[it].posterPath}",
                            title = castFilmographyList[it].title,
                            release = castFilmographyList[it].releaseDate,
                            rating = castFilmographyList[it].rating.toString()
                        )
                    }
                })
            }
        }
    }
}