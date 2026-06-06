package com.codandotv.streamplayerapp.feature.detail.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core_networking.handleError.safeRequest
import com.codandotv.streamplayerapp.feature.detail.data.model.DetailStreamResponse
import com.codandotv.streamplayerapp.feature.detail.data.model.VideoStreamsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.url

interface DetailStreamService {
    suspend fun getMovie(movieId: String): NetworkResponse<com.codandotv.streamplayerapp.feature.detail.data.model.DetailStreamResponse>
    suspend fun getVideoStreams(movieId: String): NetworkResponse<com.codandotv.streamplayerapp.feature.detail.data.model.VideoStreamsResponse>
}

class DetailStreamServiceImpl(
    private val client: HttpClient
) : com.codandotv.streamplayerapp.feature.detail.data.DetailStreamService {

    override suspend fun getMovie(movieId: String): NetworkResponse<com.codandotv.streamplayerapp.feature.detail.data.model.DetailStreamResponse> =
        client.safeRequest {
            url("movie/$movieId")
        }

    override suspend fun getVideoStreams(movieId: String): NetworkResponse<com.codandotv.streamplayerapp.feature.detail.data.model.VideoStreamsResponse> =
        client.safeRequest {
            url("movie/$movieId/videos")
        }
}
