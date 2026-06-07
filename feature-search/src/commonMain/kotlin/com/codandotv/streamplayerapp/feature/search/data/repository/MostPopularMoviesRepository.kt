package com.codandotv.streamplayerapp.feature.search.data.repository

import com.codandotv.streamplayerapp.feature.search.data.datasource.MostPopularMoviesDataSource
import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface MostPopularMoviesRepository {
    suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse>
}

@Factory(binds = [MostPopularMoviesRepository::class])
class MostPopularMoviesRepositoryImpl(
    private val dataSource: MostPopularMoviesDataSource
) : MostPopularMoviesRepository {
    override suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse> =
        dataSource.getMostPopularMovies()
}
