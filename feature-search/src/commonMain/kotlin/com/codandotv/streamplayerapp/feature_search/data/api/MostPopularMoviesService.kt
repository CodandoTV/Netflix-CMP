package com.codandotv.streamplayerapp.feature_search.data.api

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core_networking.handleError.safeRequest
import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import org.koin.core.annotation.Factory

interface MostPopularMoviesService {
    suspend fun getPopular(): NetworkResponse<ListSearchStreamResponse>
}

@Factory(binds = [MostPopularMoviesService::class])
class MostPopularMoviesServiceImpl(
    private val client: HttpClient
) : MostPopularMoviesService {
    override suspend fun getPopular(): NetworkResponse<ListSearchStreamResponse> =
        client.safeRequest {
            url("movie/popular")
        }
}
