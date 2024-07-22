package com.juliopicazo.baubapchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.juliopicazo.baubapchallenge.ui.screen.login.LoginScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.LoginScreen.route,
        modifier = modifier,
    ) {
        composable(NavigationScreen.LoginScreen.route) {
            LoginScreen()
        }
    }
}
