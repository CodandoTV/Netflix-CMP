package com.codandotv.streamplayerapp.feature.search.data.datasource

import com.codandotv.streamplayerapp.core.networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface MostPopularMoviesDataSource {
    suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse>
}

@Factory(binds = [MostPopularMoviesDataSource::class])
class MostPopularMoviesDataSourceImpl(
    private val service: com.codandotv.streamplayerapp.feature.search.data.api.MostPopularMoviesService
) : MostPopularMoviesDataSource {

    override suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse> =
        service.getPopular().toFlow()
}
