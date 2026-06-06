package com.codandotv.streamplayerapp.feature.detail.domain

import com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepository
import kotlinx.coroutines.flow.Flow

interface VideoStreamsUseCase {
    suspend fun getVideoStreams(): Flow<List<com.codandotv.streamplayerapp.feature.detail.domain.VideoStream>>
}

class VideoStreamsUseCaseImpl(
    private val detailStreamRepository: com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepository
) : com.codandotv.streamplayerapp.feature.detail.domain.VideoStreamsUseCase {
    override suspend fun getVideoStreams(): Flow<List<com.codandotv.streamplayerapp.feature.detail.domain.VideoStream>> {
        return detailStreamRepository.getVideoStreams()
    }
}
