package com.codandotv.streamplayerapp.feature.search.data.datasource

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature.search.data.api.MostPopularMoviesService
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface MostPopularMoviesDataSource {
    suspend fun getMostPopularMovies(): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse>
}

@Factory(binds = [_root_ide_package_.com.codandotv.streamplayerapp.feature.search.data.datasource.MostPopularMoviesDataSource::class])
class MostPopularMoviesDataSourceImpl(
    private val service: com.codandotv.streamplayerapp.feature.search.data.api.MostPopularMoviesService
) : com.codandotv.streamplayerapp.feature.search.data.datasource.MostPopularMoviesDataSource {

    override suspend fun getMostPopularMovies(): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse> =
        service.getPopular().toFlow()
}
