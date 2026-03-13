package com.codandotv.streamplayerapp.feature_detail.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.DETAIL_COMPLETE
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.PARAM.ID
import com.codandotv.streamplayerapp.feature_detail.di.DetailStreamModule
import com.codandotv.streamplayerapp.feature_detail.presentation.screens.DetailStreamScreen
import org.koin.compose.getKoin
import org.koin.compose.module.rememberKoinModules
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

internal const val DEFAULT_ID = "0"

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.detailStreamNavGraph(navController: NavHostController) {
    composable(DETAIL_COMPLETE) { nav ->
        rememberKoinModules {
            listOf(DetailStreamModule.module)
        }
        DetailStreamScreen(
            viewModel = koinViewModel {
                parametersOf(nav.savedStateHandle.get<String>(ID)?: DEFAULT_ID)
            },
            navController = navController,
            sharedHandlerPlatform = getKoin().get(),
            onNavigateSearchScreen = {
                navController.navigate(Routes.SEARCH)
            },
        )
    }
}
