package com.codandotv.streamplayerapp.feature.detail.presentation.screens

import com.codandotv.streamplayerapp.feature.detail.domain.DetailStream

sealed class DetailStreamsUIState {
    data class DetailStreamsLoadedUIState(
        val detailStream: DetailStream,
        val videoId: String?,
    ) : DetailStreamsUIState()

    object LoadingStreamUIState : DetailStreamsUIState()
}
