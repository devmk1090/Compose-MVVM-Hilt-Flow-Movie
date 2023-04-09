package com.devkproject.movieinfo3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devkproject.movieinfo3.screens.people.CastDetailScreen
import com.devkproject.movieinfo3.screens.detail.MovieDetailScreen
import com.devkproject.movieinfo3.screens.detail.TvDetailScreen
import com.devkproject.movieinfo3.screens.home.HomeScreen
import com.devkproject.movieinfo3.screens.more.MoreScreen
import com.devkproject.movieinfo3.screens.search.SearchScreen
import com.devkproject.movieinfo3.screens.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = "splash") {
        composable(NavigationObject.SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(NavigationObject.HOME) {
            HomeScreen(navController = navController, currentScreen = NavigationObject.HOME)
        }
        composable(NavigationObject.TV) {
            HomeScreen(navController = navController, currentScreen = NavigationObject.TV)
        }

        composable(
            NavigationObject.MORE_MOVIE.plus(NavigationObject.More.MORE_MOVIE_TYPE_PATH),
            arguments = listOf(navArgument(NavigationObject.More.MORE_MOVIE_TYPE) {
                type = NavType.StringType
            })
        ) {
            val movieType = it.arguments?.getString(NavigationObject.More.MORE_MOVIE_TYPE)
            if (movieType != null) {
                MoreScreen(
                    navController = navController,
                    currentScreen = NavigationObject.HOME,
                    type = movieType
                )
            }
        }
        composable(
            NavigationObject.MORE_TV.plus(NavigationObject.More.MORE_TV_TYPE_PATH),
            arguments = listOf(navArgument(NavigationObject.More.MORE_TV_TYPE) {
                type = NavType.StringType
            })
        ) {
            val movieType = it.arguments?.getString(NavigationObject.More.MORE_TV_TYPE)
            if (movieType != null) {
                MoreScreen(
                    navController = navController,
                    currentScreen = NavigationObject.TV,
                    type = movieType
                )
            }
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
            NavigationObject.Detail.TV_DETAIL.plus(NavigationObject.Detail.TV_DETAIL_PATH),
            arguments = listOf(navArgument(NavigationObject.Detail.TV_ITEM) {
                type = NavType.IntType
            })
        ) {
            val tvId = it.arguments?.getInt(NavigationObject.Detail.TV_ITEM)
            if (tvId != null) {
                TvDetailScreen(id = tvId, navController = navController)
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