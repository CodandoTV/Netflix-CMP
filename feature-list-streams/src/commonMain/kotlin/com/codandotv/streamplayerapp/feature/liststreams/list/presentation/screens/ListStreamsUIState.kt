package com.codandotv.streamplayerapp.feature.liststreams.list.presentation.screens

import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamsCarouselContent

data class ListStreamsUIState(
    val highlightBanner: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner? = null,
    val streamsCarouselContent: List<StreamsCarouselContent>,
    val isLoading: Boolean
)
