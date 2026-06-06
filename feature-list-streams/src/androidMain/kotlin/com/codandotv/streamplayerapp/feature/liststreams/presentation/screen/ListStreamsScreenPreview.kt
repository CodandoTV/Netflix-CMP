package com.codandotv.streamplayerapp.feature.liststreams.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews

@ThemePreviews
@Composable
fun ListStreamsScreenPreview() {
    com.codandotv.streamplayerapp.feature.liststreams.list.presentation.screens.ListStreamsScreen(
        navController = rememberNavController(),
        profilePicture = ""
    )
}
