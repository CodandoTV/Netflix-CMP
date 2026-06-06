package com.codandotv.streamplayerapp.feature.liststreams.list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codandotv.streamplayerapp.core.networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.toGenres
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.toStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ListStreamRepository {
    suspend fun getGenres(): Flow<List<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre>>

    suspend fun topRatedStream(): Flow<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>

    fun loadMovies(genre: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre): Flow<PagingData<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>>
}

class ListStreamRepositoryImpl(
    private val service: com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamService,
) : com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepository {

    override suspend fun getGenres(): Flow<List<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre>> {
        return service.getGenres().toFlow().map { it.toGenres() }
    }

    override suspend fun topRatedStream() = service.getTopRatedMovies().toFlow().map {
        it.results.first { it.poster_path != null }.toStream()
    }

    override fun loadMovies(genre: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre): Flow<PagingData<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>> {
        return Pager(
            config = PagingConfig(
                pageSize = _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepositoryImpl.Companion.PAGE_SIZE,
                maxSize = _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepositoryImpl.Companion.MAX_SIZE,
            ),
            pagingSourceFactory = {
                _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.data.StreamDataSource(
                    service,
                    genreName = genre.name,
                    genreId = genre.id
                )
            },
            initialKey = 1
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val MAX_SIZE = 500
    }
}
