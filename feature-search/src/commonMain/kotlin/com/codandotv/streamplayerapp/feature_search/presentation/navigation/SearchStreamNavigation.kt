package com.codandotv.streamplayerapp.feature_search.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.feature_search.di.SearchModule
import com.codandotv.streamplayerapp.feature_search.presentation.screens.SearchScreen
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.ksp.generated.module

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.searchStreamsNavGraph(navController: NavHostController) {
    composable(Routes.SEARCH) { _ ->
        rememberKoinModules {
            listOf(
                SearchModule().module
            )
        }
        SearchScreen(
            navController = navController,
            onNavigateDetailList = { id ->
                navController.navigate("${Routes.DETAIL}${id}")
            }
        )
    }
}
