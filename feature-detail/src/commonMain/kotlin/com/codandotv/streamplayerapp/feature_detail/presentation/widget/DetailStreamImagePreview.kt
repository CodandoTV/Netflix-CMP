package com.codandotv.streamplayerapp.feature_detail.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core_shared_ui.widget.YoutubePlayerComponentPlatform
import com.codandotv.streamplayerapp.core_shared_ui.widget.WebImage
import com.codandotv.streamplayerapp.feature_detail.presentation.screens.DetailStreamsUIState.DetailStreamsLoadedUIState
import org.jetbrains.compose.resources.painterResource
import streamplayerapp_kmp.feature_detail.generated.resources.Res
import streamplayerapp_kmp.feature_detail.generated.resources.play_circle

@Suppress("MagicNumber")
@Composable
fun DetailStreamImagePreview(
    uiState: DetailStreamsLoadedUIState,
    modifier: Modifier = Modifier,
    showPlayer: Boolean = false,
    onPlayEvent: (() -> Unit)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        contentAlignment = Alignment.Center
    ) {
        if (showPlayer) {
            YoutubePlayerComponentPlatform(
                videoId = uiState.videoId.orEmpty(),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxSize()
            )
        } else {
            WebImage(
                imageUrl = uiState.detailStream.url,
                contentScale = ContentScale.Fit,
                contentDescription = uiState.detailStream.tagline,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                    .size(50.dp)
                    .align(Alignment.Center),
            )
            Icon(
                painter = painterResource(Res.drawable.play_circle),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
                    .clickable {
                        onPlayEvent()
                    }
            )
        }
    }
}