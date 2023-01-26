package com.bhdev1215.movieinfo3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bhdev1215.movieinfo3.screens.home.HomeScreen
import com.bhdev1215.movieinfo3.screens.more.MoreScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(navController, startDestination = "home", modifier) {
        composable(NavigationObject.HOME) {
            HomeScreen()
        }
        composable(NavigationObject.HOME) {
            MoreScreen(navController = navController)
        }
    }
}