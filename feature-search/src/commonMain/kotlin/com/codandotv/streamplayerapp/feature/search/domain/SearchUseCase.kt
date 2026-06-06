package com.codandotv.streamplayerapp.feature.search.domain

import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature.search.data.repository.SearchStreamRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface SearchUseCase {
    suspend operator fun invoke(query:String): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse>
}

@Factory(binds = [_root_ide_package_.com.codandotv.streamplayerapp.feature.search.domain.SearchUseCase::class])
class SearchUseCaseImpl(val repository: com.codandotv.streamplayerapp.feature.search.data.repository.SearchStreamRepository) :
    com.codandotv.streamplayerapp.feature.search.domain.SearchUseCase {
    override suspend operator fun invoke(query:String): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse> {
        return repository.getMovieSearch(query = query)
    }
}
