package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes.HOME_COMPLETE
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes.PARAM.PROFILE_ID
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.DETAIL
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.PROFILE_PICKER
import com.codandotv.streamplayerapp.feature_list_streams.list.di.ListStreamModule
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamsScreen
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

internal const val DEFAULT_ID = ""

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.listStreamsNavGraph(navController: NavHostController) {
    composable(HOME_COMPLETE) { nav ->
        rememberKoinModules {
            listOf(ListStreamModule.module)
        }

        ListStreamsScreen(
            navController = navController,
            onNavigateDetailList = { id ->
                navController.navigate("${DETAIL}${id}")
            },
            onNavigateProfilePicker = {
                navController.navigate(PROFILE_PICKER)
            },
            onNavigateSearchScreen = {
                navController.navigate(Routes.SEARCH)
            },
            profilePicture = nav.savedStateHandle.get<String>(PROFILE_ID) ?: DEFAULT_ID
        )
    }
}
