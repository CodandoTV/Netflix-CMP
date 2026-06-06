package com.codandotv.streamplayerapp.feature.search.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core.navigation.routes.Routes
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.searchStreamsNavGraph(navController: NavHostController) {
    composable(Routes.SEARCH) { _ ->
        _root_ide_package_.com.codandotv.streamplayerapp.feature.search.presentation.screens.SearchScreen(
            navController = navController,
            onNavigateDetailList = { id ->
                navController.navigate("${Routes.DETAIL}${id}")
            }
        )
    }
}
