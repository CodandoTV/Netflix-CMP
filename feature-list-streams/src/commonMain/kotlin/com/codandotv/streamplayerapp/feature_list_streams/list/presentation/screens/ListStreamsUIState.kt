package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamsCarouselContent

data class ListStreamsUIState(
    val highlightBanner: HighlightBanner? = null,
    val streamsCarouselContent: List<StreamsCarouselContent>,
    val isLoading: Boolean
)