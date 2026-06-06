package com.codandotv.streamplayerapp.composeApp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.composeApp.presentation.screens.SplashScreen
import com.codandotv.streamplayerapp.core.navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core.navigation.routes.Routes

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
