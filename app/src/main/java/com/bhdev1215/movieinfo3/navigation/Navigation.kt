package com.bhdev1215.movieinfo3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bhdev1215.movieinfo3.screens.components.CastDetailScreen
import com.bhdev1215.movieinfo3.screens.detail.MovieDetailScreen
import com.bhdev1215.movieinfo3.screens.home.HomeScreen
import com.bhdev1215.movieinfo3.screens.more.MoreScreen
import com.bhdev1215.movieinfo3.screens.search.SearchScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = "home") {
        composable(NavigationObject.HOME) {
            HomeScreen(navController = navController, currentScreen = NavigationObject.HOME)
        }
        composable(NavigationObject.MORE) {
            MoreScreen(navController = navController)
        }
        composable(NavigationObject.CAST) {

        }
        composable(NavigationObject.TV) {
            HomeScreen(navController = navController, currentScreen = NavigationObject.TV)
        }
        composable(NavigationObject.SEARCH) {
            SearchScreen(navController = navController)
        }
        composable(
            NavigationObject.Detail.MOVIE_DETAIL.plus(NavigationObject.Detail.MOVIE_DETAIL_PATH),
            arguments = listOf(navArgument(NavigationObject.Detail.MOVIE_ITEM) {
                type = NavType.IntType
            })
        ) {
            val movieId = it.arguments?.getInt(NavigationObject.Detail.MOVIE_ITEM)
            if (movieId != null) {
                MovieDetailScreen(id = movieId, navController = navController)
            }
        }
        composable(
            NavigationObject.CastDetail.CAST_DETAIL.plus(NavigationObject.CastDetail.CAST_DETAIL_PATH),
            arguments = listOf(navArgument(NavigationObject.CastDetail.CAST_ID) {
                type = NavType.IntType
            })
        ) {
            val castId = it.arguments?.getInt(NavigationObject.CastDetail.CAST_ID)
            if (castId != null) {
                CastDetailScreen(id = castId, navController = navController)
            }
        }
    }
}