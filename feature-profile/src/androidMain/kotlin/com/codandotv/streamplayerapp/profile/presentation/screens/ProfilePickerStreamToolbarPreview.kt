package com.codandotv.streamplayerapp.profile.presentation.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.profile.presentation.widget.ProfilePickerStreamToolbar

@ThemePreviews
@Composable
fun ProfilePickerStreamToolbarPreview() {
    MaterialTheme {
        ProfilePickerStreamToolbar()
    }
}
