package com.codandotv.streamplayerapp.feature.profile.presentation.screens

import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream

@ThemePreviews
@Composable
fun SetupProfilePickerScreenPreview() {
    ThemePreview {
        SetupProfilePickerScreen(
            uiState = ProfilePickerStreamsUIState(
                selectedItem = ProfileStream(
                    "1",
                    "Name",
                    ""
                ),
            )
        )
    }
}
