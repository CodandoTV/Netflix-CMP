package com.codandotv.streamplayerapp.feature_search.data.datasource

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_search.data.api.MostPopularMoviesService
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface MostPopularMoviesDataSource {
    suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse>
}

@Factory(binds = [MostPopularMoviesDataSource::class])
class MostPopularMoviesDataSourceImpl(
    private val service: MostPopularMoviesService
) : MostPopularMoviesDataSource {

    override suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse> =
        service.getPopular().toFlow()
}
