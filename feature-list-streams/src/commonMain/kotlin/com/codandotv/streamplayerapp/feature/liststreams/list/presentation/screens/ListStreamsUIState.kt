package com.codandotv.streamplayerapp.feature.liststreams.list.presentation.screens

import com.codandotv.streamplayerapp.core.shared.ui.widget.StreamsCarouselContent
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner

data class ListStreamsUIState(
    val highlightBanner: HighlightBanner? = null,
    val streamsCarouselContent: List<StreamsCarouselContent>,
    val isLoading: Boolean
)
