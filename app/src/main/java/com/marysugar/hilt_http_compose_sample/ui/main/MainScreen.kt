package com.marysugar.hilt_http_compose_sample.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import com.marysugar.hilt_http_compose_sample.ui.posters.Posters

@Composable
fun MainScreen() {
  val navController = rememberNavController()

  ProvideWindowInsets {
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
      composable(NavScreen.Home.route) {
        Posters(
          viewModel = hiltViewModel(),
          selectPoster = {
            navController.navigate("${NavScreen.PosterDetails.route}/$it")
          }

        )
      }
      composable(
        route = NavScreen.PosterDetails.routeWithArgument,
        arguments = listOf(
          navArgument(NavScreen.PosterDetails.argument0) { type = NavType.LongType }
        )
      ) { backStackEntry ->
        val posterId =
          backStackEntry.arguments?.getLong(NavScreen.PosterDetails.argument0)
            ?: return@composable
//        PosterDetails(posterId = posterId, viewModel = hiltViewModel()) {
//          navController.navigateUp()
//        }
      }
    }
  }
}

sealed class NavScreen(val route: String) {

  object Home : NavScreen("Home")

  object PosterDetails : NavScreen("PosterDetails") {

    const val routeWithArgument: String = "PosterDetails/{posterId}"

    const val argument0: String = "posterId"
  }
}