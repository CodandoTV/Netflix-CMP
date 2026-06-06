package com.codandotv.streamplayerapp.feature.detail.presentation.screens

import com.codandotv.streamplayerapp.feature.detail.domain.DetailStream

sealed class DetailStreamsUIState {
    data class DetailStreamsLoadedUIState(
        val detailStream: com.codandotv.streamplayerapp.feature.detail.domain.DetailStream,
        val videoId: String?,
    ) : com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState()

    object LoadingStreamUIState : com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState()
}
