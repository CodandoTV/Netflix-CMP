package com.codandotv.streamplayerapp.feature_search.data.repository

import com.codandotv.streamplayerapp.feature_search.data.datasource.SearchStreamDataSource
import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface SearchStreamRepository {
    suspend fun getMovieSearch(query: String): Flow<ListSearchStreamResponse>

}

@Factory(binds = [SearchStreamRepository::class])
class SearchStreamRepositoryImp(
    private val dataSource: SearchStreamDataSource
) : SearchStreamRepository {
    override suspend fun getMovieSearch(query: String): Flow<ListSearchStreamResponse> =
        dataSource.getMovieSearch(query)
}
