package com.codandotv.streamplayerapp.feature_search.data.datasource

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_search.data.api.SearchStreamService
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface SearchStreamDataSource {
    suspend fun getMovieSearch(query: String): Flow<ListSearchStreamResponse>
}

@Factory(binds = [SearchStreamDataSource::class])
class SearchStreamDataSourceImpl(
    private val service: SearchStreamService
): SearchStreamDataSource {

    override suspend fun getMovieSearch(query:String): Flow<ListSearchStreamResponse> =
        service.getSearch(query = query).toFlow()
}
