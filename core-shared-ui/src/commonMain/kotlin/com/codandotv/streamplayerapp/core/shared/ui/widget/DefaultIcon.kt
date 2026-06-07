package com.codandotv.streamplayerapp.core.shared.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DefaultIcon(
    modifier: Modifier = Modifier,
    searchIcon: ImageVector = Icons.Default.Search,
    iconColor: Color = Color.White,
    contentDescription: String = "",
    onIconClickAction: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = onIconClickAction
    ) {
        Icon(
            imageVector = searchIcon,
            contentDescription = contentDescription,
            tint = iconColor
        )
    }
}
