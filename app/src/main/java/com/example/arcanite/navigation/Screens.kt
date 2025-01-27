package com.example.arcanite.navigation

sealed class Screens(
    val screen: String,
) {
    data object Splash: Screens("splash")
    data object Main: Screens("main")
    data object File : Screens("file/{owner}/{repo}/{path}") {
        fun createRoute(owner: String, repo: String, path: String) = "file/$owner/$repo/$path"
    }
}