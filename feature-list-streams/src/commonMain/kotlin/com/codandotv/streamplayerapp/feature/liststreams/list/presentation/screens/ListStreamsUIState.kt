package com.codandotv.streamplayerapp.feature.liststreams.list.presentation.screens

import com.codandotv.streamplayerapp.core.shared.ui.widget.StreamsCarouselContent

data class ListStreamsUIState(
    val highlightBanner: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner? = null,
    val streamsCarouselContent: List<com.codandotv.streamplayerapp.core.shared.ui.widget.StreamsCarouselContent>,
    val isLoading: Boolean
)
