package com.example.arcanite.navigation

sealed class Screens(
    val screen: String,
) {
    data object Splash: Screens("splash")
    data object Main: Screens("main")
}