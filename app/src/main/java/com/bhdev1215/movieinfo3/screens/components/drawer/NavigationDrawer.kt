package com.bhdev1215.movieinfo3.screens.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bhdev1215.movieinfo3.R
import com.bhdev1215.movieinfo3.model.NavigationDrawerItem
import com.bhdev1215.movieinfo3.navigation.NavigationObject

@Composable
fun NavigationDrawer(
    currentScreen: String,
    itemClick: (String) -> Unit
) {
    val menuList = createDrawerItem(currentScreen)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(26, 154, 255, 255),
                        Color(154, 210, 255, 255)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            DrawerHeader()
        }
        items(menuList.size) { item ->
            DrawerBody(menuList[item], itemClick)
        }
    }
}

@Composable
private fun createDrawerItem(currentScreen: String) = listOf(
    NavigationDrawerItem(
        image = painterResource(R.drawable.ic_baseline_movie_24),
        isSelected = currentScreen == NavigationObject.HOME,
        title = "home"
    ),
    NavigationDrawerItem(
        image = painterResource(R.drawable.ic_baseline_tv_24),
        isSelected = currentScreen == NavigationObject.TV,
        title = "tv"
    )
)