package com.codandotv.streamplayerapp.feature.detail.domain

import kotlinx.coroutines.flow.Flow

interface VideoStreamsUseCase {
    suspend fun getVideoStreams(): Flow<List<VideoStream>>
}

class VideoStreamsUseCaseImpl(
    private val detailStreamRepository: DetailStreamRepository
) : VideoStreamsUseCase {
    override suspend fun getVideoStreams(): Flow<List<VideoStream>> {
        return detailStreamRepository.getVideoStreams()
    }
}
