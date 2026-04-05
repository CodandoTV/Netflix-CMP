package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.emptyFlow

@Composable
@Preview
fun StreamsCarouselPreview() {
    StreamsCarousel(
        content = StreamsCarouselContent(
            genreTitle = "Ação",
            contentList = emptyFlow()
        )
    )
}
