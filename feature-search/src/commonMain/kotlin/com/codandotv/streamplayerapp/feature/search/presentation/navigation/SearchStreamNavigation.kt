package com.codandotv.streamplayerapp.feature.search.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core.navigation.routes.Routes
import com.codandotv.streamplayerapp.feature.search.presentation.screens.SearchScreen
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.searchStreamsNavGraph(navController: NavHostController) {
    composable(Routes.SEARCH) { _ ->
        SearchScreen(
            navController = navController,
            onNavigateDetailList = { id ->
                navController.navigate("${Routes.DETAIL}$id")
            }
        )
    }
}
