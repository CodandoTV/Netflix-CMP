package com.codandotv.streamplayerapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.codandotv.streamplayerapp.core.shared.ui.theme.StreamPlayerTheme
import com.codandotv.streamplayerapp.navigation.NavigationGraph

@Composable
fun StreamPlayerApp() {
    com.codandotv.streamplayerapp.core.shared.ui.theme.StreamPlayerTheme {
        val navController = rememberNavController()
        NavigationGraph(navController = navController)
    }
}
