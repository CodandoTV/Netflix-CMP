package com.codandotv.streamplayerapp.feature.search.data.repository

import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature.search.data.datasource.MostPopularMoviesDataSource
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface MostPopularMoviesRepository {
    suspend fun getMostPopularMovies(): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse>
}

@Factory(binds = [_root_ide_package_.com.codandotv.streamplayerapp.feature.search.data.repository.MostPopularMoviesRepository::class])
class MostPopularMoviesRepositoryImpl(
    private val dataSource: com.codandotv.streamplayerapp.feature.search.data.datasource.MostPopularMoviesDataSource
) : com.codandotv.streamplayerapp.feature.search.data.repository.MostPopularMoviesRepository {
    override suspend fun getMostPopularMovies(): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse> =
        dataSource.getMostPopularMovies()
}
