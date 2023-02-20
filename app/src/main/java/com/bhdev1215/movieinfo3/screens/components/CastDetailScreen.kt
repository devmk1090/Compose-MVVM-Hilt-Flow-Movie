package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.model.artist.CastDetail
import com.bhdev1215.movieinfo3.model.artist.CastFilmography
import com.bhdev1215.movieinfo3.screens.detail.DetailViewModel
import com.bhdev1215.movieinfo3.ui.theme.cornerRadius10
import com.bhdev1215.movieinfo3.util.Constants
import com.bhdev1215.movieinfo3.util.Resource
import timber.log.Timber

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

    Box {
        if (castDetail is Resource.Success) {
            Column {
                CommonAppBar(
                    title = {
                        Text(text = data!!.name, color = Color.White, fontSize = 18.sp)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    showBackArrow = true,
                    navController = navController
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
                                    placeholder(R.drawable.ic_person_white)
                                    error(R.drawable.ic_person_white)
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
                            Text(text = data.name, color = Color.White, fontSize = 16.sp)
                            Text(text = data.birthday, color = Color.White, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}