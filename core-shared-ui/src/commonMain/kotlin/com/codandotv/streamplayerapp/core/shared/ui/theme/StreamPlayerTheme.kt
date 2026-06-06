package com.codandotv.streamplayerapp.core.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core.shared.ui.resources.Colors

@Composable
fun StreamPlayerTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = _root_ide_package_.com.codandotv.streamplayerapp.core.shared.ui.theme.getColorScheme(
            isDarkTheme
        ),
        content = content,
    )
}

private fun getColorScheme(isDarkTheme: Boolean) =
    if (isDarkTheme) {
        _root_ide_package_.com.codandotv.streamplayerapp.core.shared.ui.resources.Colors.DarkColors
    } else {
        _root_ide_package_.com.codandotv.streamplayerapp.core.shared.ui.resources.Colors.LightColors
    }
