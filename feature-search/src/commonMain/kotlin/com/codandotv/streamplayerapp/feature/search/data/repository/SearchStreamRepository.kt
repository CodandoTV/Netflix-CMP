package com.codandotv.streamplayerapp.feature.search.data.repository

import com.codandotv.streamplayerapp.feature.search.data.datasource.SearchStreamDataSource
import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface SearchStreamRepository {
    suspend fun getMovieSearch(query: String): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse>

}

@Factory(binds = [_root_ide_package_.com.codandotv.streamplayerapp.feature.search.data.repository.SearchStreamRepository::class])
class SearchStreamRepositoryImp(
    private val dataSource: com.codandotv.streamplayerapp.feature.search.data.datasource.SearchStreamDataSource
) : com.codandotv.streamplayerapp.feature.search.data.repository.SearchStreamRepository {
    override suspend fun getMovieSearch(query: String): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse> =
        dataSource.getMovieSearch(query)
}
