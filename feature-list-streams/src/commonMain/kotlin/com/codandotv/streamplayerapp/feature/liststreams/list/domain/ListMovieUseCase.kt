package com.codandotv.streamplayerapp.feature.liststreams.list.domain

import androidx.paging.PagingData
import com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream
import kotlinx.coroutines.flow.Flow

interface ListStreamUseCase {
    operator fun invoke(genre: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre): Flow<PagingData<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>>
}

class ListStreamUseCaseImpl(
    private val repository: com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepository
) : com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamUseCase {
    override operator fun invoke(genre: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre): Flow<PagingData<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>> {
        return repository.loadMovies(genre)
    }
}
