package com.codandotv.streamplayerapp.feature.search.data.datasource

import com.codandotv.streamplayerapp.core.networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface SearchStreamDataSource {
    suspend fun getMovieSearch(query: String): Flow<ListSearchStreamResponse>
}

@Factory(binds = [SearchStreamDataSource::class])
class SearchStreamDataSourceImpl(
    private val service: com.codandotv.streamplayerapp.feature.search.data.api.SearchStreamService
) : SearchStreamDataSource {

    override suspend fun getMovieSearch(query: String): Flow<ListSearchStreamResponse> =
        service.getSearch(query = query).toFlow()
}
