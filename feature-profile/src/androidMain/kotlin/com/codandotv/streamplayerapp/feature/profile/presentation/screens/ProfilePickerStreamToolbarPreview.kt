package com.codandotv.streamplayerapp.feature.profile.presentation.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core.shared.ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature.profile.presentation.widget.ProfilePickerStreamToolbar

@ThemePreviews
@Composable
fun ProfilePickerStreamToolbarPreview() {
    MaterialTheme {
        ProfilePickerStreamToolbar()
    }
}
