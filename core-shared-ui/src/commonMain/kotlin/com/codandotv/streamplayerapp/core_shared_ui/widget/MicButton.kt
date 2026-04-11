package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.core_shared_ui.generated.resources.Res
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_mic

@Composable
fun MicButton(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Default.Check,
        contentDescription = stringResource(Res.string.icon_mic),
        onIconClickAction = action,
        iconColor = Color.Gray
    )
}
