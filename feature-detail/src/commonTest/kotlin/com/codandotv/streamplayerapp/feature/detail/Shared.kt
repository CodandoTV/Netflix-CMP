package com.codandotv.streamplayerapp.feature.detail

import com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState

val videoStream =
    _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.domain.VideoStream(
        movieId = 123,
        videoId = "123"
    )

val fakeStream =
    _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.domain.DetailStream(
        id = "1",
        title = "Fake Movie",
        overview = "Overview of the fake movie",
        tagline = "The ultimate test movie",
        url = "https://example.com/fake.jpg",
        releaseYear = "2025",
        isFavorite = false
    )

val expectedDetailStream =
    _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.data.model.DetailStreamResponse(
        id = 1L,
        title = "Fake Movie",
        overview = "This is a fake overview.",
        tagline = "Fake Tagline",
        backdrop_path = "aaaa",
        release_date = "2025"
    )
val expectedDetailStreamLoadedUI = DetailStreamsUIState.DetailStreamsLoadedUIState(
    detailStream = fakeStream,
    videoId = "123"
)
