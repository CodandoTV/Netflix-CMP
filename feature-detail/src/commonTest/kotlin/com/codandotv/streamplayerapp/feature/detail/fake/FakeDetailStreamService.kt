package com.codandotv.streamplayerapp.feature.detail.fake

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature.detail.data.DetailStreamService
import com.codandotv.streamplayerapp.feature.detail.data.model.DetailStreamResponse
import com.codandotv.streamplayerapp.feature.detail.data.model.VideoStreamsResponse

class FakeDetailStreamService :
    DetailStreamService {
    var getMovieCalled = false
    var getVideoStreamsCalled = false

    var movieResponse: NetworkResponse<DetailStreamResponse> = NetworkResponse.Success(
        DetailStreamResponse(
            id = 1L,
            title = "Fake Movie",
            overview = "This is a fake overview.",
            tagline = "Fake Tagline",
            backdrop_path = "/fake_backdrop.jpg",
            release_date = "2025-01-01"
        )
    )

    var videoStreamsResponse: NetworkResponse<VideoStreamsResponse> = NetworkResponse.Success(
        VideoStreamsResponse(
            results = emptyList(),
            id = 1L
        )
    )

    override suspend fun getMovie(movieId: String): NetworkResponse<DetailStreamResponse> {
        getMovieCalled = true
        return movieResponse
    }

    override suspend fun getVideoStreams(movieId: String): NetworkResponse<VideoStreamsResponse> {
        getVideoStreamsCalled = true
        return videoStreamsResponse
    }
}
