@file:Suppress("MagicNumber")

package com.codandotv.streamplayerapp.core.shared.ui.resources

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
object Colors {
    val Dark10 = Color(0x1A000000)
    val AlphaBlack = Color(0xEB000000)

    val Gray100 = Color(0xFF2C2C2C)

    val LightColors = lightColorScheme(
        primary = COLOR_PRIMARY,
        secondary = Color(0xFFF5F5F1),
        background = COLOR_BACKGROUND,
        onBackground = Color(0xFFFFFFFF),
        surface = Color(0xFF121212),
        onSurface = Color(0xFFF5F5F1),
        onSurfaceVariant = Color(0XFF7b7b7b)
    )

    val DarkColors = darkColorScheme(
        primary = COLOR_PRIMARY,
        secondary = Color(0xFFF5F5F1),
        background = COLOR_BACKGROUND,
        onBackground = Color(0xFFFFFFFF),
        surface = Color(0xFF121212),
        onSurface = Color(0xFFF5F5F1),
        onSurfaceVariant = Color(0XFF7b7b7b)
    )
}

val COLOR_PRIMARY = Color(0xFFE50914)
val COLOR_BACKGROUND = Color(0xFF000000)
