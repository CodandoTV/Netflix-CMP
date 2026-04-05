package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun StreamPlayerTopBarPreview() {
    ThemePreview {
        StreamPlayerTopBar(
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
                rememberTopAppBarState()
            ),
            onNavigateProfilePicker = {},
            onSelectedProfilePicture = ""
        )
    }
}
