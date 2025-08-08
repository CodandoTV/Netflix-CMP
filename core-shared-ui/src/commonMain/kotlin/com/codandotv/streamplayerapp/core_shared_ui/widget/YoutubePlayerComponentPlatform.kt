@file:Suppress("EXPECT_AND_ACTUAL_IN_THE_SAME_MODULE")

package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun YoutubePlayerComponentPlatform(videoId: String, modifier: Modifier = Modifier)


internal fun String.videoIdToEmbedHTML(): String {
    val iframeProperties = "width=\"100%\" height=\"100%\""
    return "<iframe $iframeProperties" +
            "src=\"https://www.youtube.com/embed/$this?playsinline=1\" " +
            "frameborder=\"0\" allowfullscreen></iframe>"
}

