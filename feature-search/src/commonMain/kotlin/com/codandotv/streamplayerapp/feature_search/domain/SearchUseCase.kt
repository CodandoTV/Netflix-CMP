package com.codandotv.streamplayerapp.feature_search.domain

import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_search.data.repository.SearchStreamRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface SearchUseCase {
    suspend operator fun invoke(query:String): Flow<ListSearchStreamResponse>
}

@Factory(binds = [SearchUseCase::class])
class SearchUseCaseImpl(val repository: SearchStreamRepository) : SearchUseCase {
    override suspend operator fun invoke(query:String): Flow<ListSearchStreamResponse> {
        return repository.getMovieSearch(query = query)
    }
}
