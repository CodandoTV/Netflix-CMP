package com.codandotv.streamplayerapp.feature_news.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.DETAIL_COMPLETE
import com.codandotv.streamplayerapp.feature_news.di.NewsScreenModule
import com.codandotv.streamplayerapp.feature_news.presentation.screens.NewsScreen
import com.codandotv.streamplayerapp.feature_news.presentation.screens.NewsScreenContent
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.newsStreamNavGraph(navController: NavHostController) {
    composable(BottomNavRoutes.NEWS) { _ ->
        rememberKoinModules {
            listOf(NewsScreenModule.module,PermissionsModule.module)
        }
        NewsScreenContent(navController)
    }
}
