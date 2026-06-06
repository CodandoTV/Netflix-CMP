package com.codandotv.streamplayerapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.bottomnavigation.StreamPlayerBottomNavigation
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.navigation.listStreamsNavGraph
import com.codandotv.streamplayerapp.feature.profile.presentation.navigation.profilePickerStreamNavGraph
import com.codandotv.streamplayerapp.presentation.navigation.splashNavGraph
import com.codandotv.streamplayerapp.feature_detail.presentation.navigation.detailStreamNavGraph
import com.codandotv.streamplayerapp.feature_news.presentation.navigation.newsStreamNavGraph
import com.codandotv.streamplayerapp.feature.search.presentation.navigation.searchStreamsNavGraph

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
    ) {
        splashNavGraph(navController = navController)
        listStreamsNavGraph(navController = navController)
        searchStreamsNavGraph(navController = navController)
        detailStreamNavGraph(navController = navController)
        profilePickerStreamNavGraph(navController = navController)
        newsStreamNavGraph(navController = navController)
        temporaryFun(BottomNavRoutes.GAMES, navController)
        temporaryFun(BottomNavRoutes.DOWNLOADS, navController)
    }
}

fun NavGraphBuilder.temporaryFun(route: String, navController: NavController) {
    composable(route = route) {
        example(navController = navController, route)
    }
}

@Composable
fun example(navController: NavController, route: String) {
    Scaffold(
        bottomBar = {
            StreamPlayerBottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            text = route,
            color = Color.White
        )
    }
}
