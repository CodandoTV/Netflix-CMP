package com.codandotv.streamplayerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.presentation.screens.SplashScreen

fun NavGraphBuilder.splashNavGraph(navController: NavHostController) {
    composable(Routes.Splash) {
        SplashScreen(
            onAnimationFinished = {
                navController.navigate(BottomNavRoutes.HOME) {
                    popUpTo(Routes.Splash) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}