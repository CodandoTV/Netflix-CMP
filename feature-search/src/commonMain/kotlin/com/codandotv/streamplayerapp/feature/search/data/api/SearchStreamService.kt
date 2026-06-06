package com.codandotv.streamplayerapp.feature.search.data.api

import com.codandotv.streamplayerapp.core.networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core.networking.handleError.safeRequest
import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided

interface SearchStreamService {
    suspend fun getSearch(query: String): NetworkResponse<ListSearchStreamResponse>
}

@Factory(binds = [_root_ide_package_.com.codandotv.streamplayerapp.feature.search.data.api.SearchStreamService::class])
class SearchStreamServiceImpl(
    @Provided private val client: HttpClient
) : com.codandotv.streamplayerapp.feature.search.data.api.SearchStreamService {
    override suspend fun getSearch(query: String): NetworkResponse<ListSearchStreamResponse> =
        client.safeRequest {
            url("search/movie")
            parameter("query", query)
        }
}
