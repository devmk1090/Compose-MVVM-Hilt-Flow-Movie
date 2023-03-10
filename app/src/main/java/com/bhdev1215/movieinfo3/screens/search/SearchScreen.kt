package com.bhdev1215.movieinfo3.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Box {
        Column {
            CommonAppBar(
                title = {
                    Text(text = "검색", color = Color.White, fontSize = 18.sp)
                },
                modifier = Modifier.fillMaxWidth(),
                showBackArrow = true,
                navController = navController,
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            )
            Text(text = "this is search screen")
        }
    }
}