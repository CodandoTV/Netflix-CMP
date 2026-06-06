package com.codandotv.streamplayerapp.feature.liststreams.list.domain

import com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream
import kotlinx.coroutines.flow.Flow

interface GetTopRatedStream {
    suspend operator fun invoke(): Flow<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>
}

class GetTopRatedStreamImpl(
    private val repository: com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepository
) : com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetTopRatedStream {
    override suspend operator fun invoke(): Flow<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream> {
        return repository.topRatedStream()
    }
}
