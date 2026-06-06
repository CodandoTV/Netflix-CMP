package com.codandotv.streamplayerapp.feature.profile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core.navigation.routes.BottomNavRoutes.HOME
import com.codandotv.streamplayerapp.core.navigation.routes.BottomNavRoutes.PARAM.PROFILE_ID
import com.codandotv.streamplayerapp.core.navigation.routes.Routes
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.profilePickerStreamNavGraph(navController: NavHostController) {
    composable(Routes.PROFILE_PICKER) {
        _root_ide_package_.com.codandotv.streamplayerapp.feature.profile.presentation.screens.ProfilePickerStreamScreen(
            onNavigateListStreams = { profilePic ->
                navController.navigate("$HOME?$PROFILE_ID=$profilePic")
            }
        )
    }
}
