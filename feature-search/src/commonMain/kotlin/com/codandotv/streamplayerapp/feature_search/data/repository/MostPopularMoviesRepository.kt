package com.codandotv.streamplayerapp.feature_search.data.repository

import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_search.data.datasource.MostPopularMoviesDataSource
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
