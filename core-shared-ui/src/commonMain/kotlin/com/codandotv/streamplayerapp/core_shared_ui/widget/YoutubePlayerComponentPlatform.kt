@file:Suppress("EXPECT_AND_ACTUAL_IN_THE_SAME_MODULE")

package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun YoutubePlayerComponentPlatform(videoId: String, modifier: Modifier = Modifier)


internal fun String.videoIdToEmbedHTML(): String {
    return """
        <iframe width="100%" height="100%"
            src="https://www.youtube.com/embed/$this?playsinline=1"
            frameborder="0" allowfullscreen referrerpolicy="strict-origin-when-cross-origin"/>
    """.trimIndent()
}

internal const val CODANDOTV_DOMAIN = "https://com.codandotv.com/codandotv"
