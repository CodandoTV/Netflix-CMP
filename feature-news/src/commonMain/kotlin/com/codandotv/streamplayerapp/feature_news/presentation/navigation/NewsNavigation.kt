package com.codandotv.streamplayerapp.feature_news.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.feature_news.presentation.screens.NewsScreenContent
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.newsStreamNavGraph(navController: NavHostController) {
    composable(BottomNavRoutes.NEWS) { _ ->
        NewsScreenContent(navController)
    }
}
