package com.codandotv.streamplayerapp.feature.detail.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core.navigation.routes.Routes.DETAIL_COMPLETE
import com.codandotv.streamplayerapp.core.navigation.routes.Routes.PARAM.ID
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
            listOf(_root_ide_package_.com.codandotv.streamplayerapp.feature.detail.di.DetailStreamModule.module)
        }
        _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamScreen(
            viewModel = koinViewModel {
                parametersOf(
                    nav.savedStateHandle.get<String>(ID)
                        ?: _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.presentation.navigation.DEFAULT_ID
                )
            },
            navController = navController,
            sharedHandlerPlatform = getKoin().get(),
        )
    }
}
