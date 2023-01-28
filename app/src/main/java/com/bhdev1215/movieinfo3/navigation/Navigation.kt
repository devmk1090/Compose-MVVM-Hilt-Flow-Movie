package com.bhdev1215.movieinfo3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bhdev1215.movieinfo3.screens.home.HomeScreen
import com.bhdev1215.movieinfo3.screens.more.MoreScreen

@Composable
fun Navigation(
    navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable(NavigationObject.HOME) {
            HomeScreen(navController = navController)
        }
        composable(NavigationObject.MORE) {
            MoreScreen(navController = navController)
        }
    }
}