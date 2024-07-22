package com.juliopicazo.baubapchallenge.ui.navigation

sealed class NavigationScreen(
    val route: String,
    val baseRoute: String,
) {
    object LoginScreen : NavigationScreen(
        route = "login_screen",
        baseRoute = "login_screen",
    )
}
