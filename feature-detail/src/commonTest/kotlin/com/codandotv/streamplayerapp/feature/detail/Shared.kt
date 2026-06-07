package com.codandotv.streamplayerapp.feature.detail

import com.codandotv.streamplayerapp.feature.detail.data.model.DetailStreamResponse
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature.detail.domain.VideoStream
import com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState

val videoStream = VideoStream(
    movieId = 123,
    videoId = "123"
)

val fakeStream =
    DetailStream(
        id = "1",
        title = "Fake Movie",
        overview = "Overview of the fake movie",
        tagline = "The ultimate test movie",
        url = "https://example.com/fake.jpg",
        releaseYear = "2025",
        isFavorite = false
    )

val expectedDetailStream =
    DetailStreamResponse(
        id = 1L,
        title = "Fake Movie",
        overview = "This is a fake overview.",
        tagline = "Fake Tagline",
        backdropPath = "aaaa",
        releaseDate = "2025"
    )
val expectedDetailStreamLoadedUI = DetailStreamsUIState.DetailStreamsLoadedUIState(
    detailStream = fakeStream,
    videoId = "123"
)
