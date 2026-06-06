package com.codandotv.streamplayerapp.feature.search.data.datasource

import com.codandotv.streamplayerapp.core.networking.handleError.toFlow
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface SearchStreamDataSource {
    suspend fun getMovieSearch(query: String): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse>
}

@Factory(binds = [_root_ide_package_.com.codandotv.streamplayerapp.feature.search.data.datasource.SearchStreamDataSource::class])
class SearchStreamDataSourceImpl(
    private val service: com.codandotv.streamplayerapp.feature.search.data.api.SearchStreamService
): com.codandotv.streamplayerapp.feature.search.data.datasource.SearchStreamDataSource {

    override suspend fun getMovieSearch(query:String): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse> =
        service.getSearch(query = query).toFlow()
}
