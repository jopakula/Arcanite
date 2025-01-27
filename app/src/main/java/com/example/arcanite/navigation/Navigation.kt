package com.example.arcanite.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.arcanite.ui.screens.FileScreen
import com.example.arcanite.ui.screens.MainScreen
import com.example.arcanite.ui.screens.SplashScreen

@Composable
fun Navigation(
    navigationController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigationController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(Screens.Main.screen) { MainScreen(navigationController = navigationController) }
        composable(Screens.Splash.screen) { SplashScreen(navigationController = navigationController) }
        composable(
            route = Screens.File.screen,
            arguments = listOf(
                navArgument("owner") { type = NavType.StringType },
                navArgument("repo") { type = NavType.StringType },
                navArgument("path") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val owner = backStackEntry.arguments?.getString("owner") ?: ""
            val repo = backStackEntry.arguments?.getString("repo") ?: ""
            val path = backStackEntry.arguments?.getString("path") ?: ""
            FileScreen(navigationController, owner, repo, path)
        }
    }
}
