package com.codandotv.streamplayerapp.composeApp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.codandotv.streamplayerapp.composeApp.presentation.navigation.NavigationGraph
import com.codandotv.streamplayerapp.core.shared.ui.theme.StreamPlayerTheme

@Composable
fun StreamPlayerApp() {
    StreamPlayerTheme {
        val navController = rememberNavController()
        NavigationGraph(navController = navController)
    }
}
