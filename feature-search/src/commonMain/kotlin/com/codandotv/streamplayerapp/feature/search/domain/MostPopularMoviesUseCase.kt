package com.codandotv.streamplayerapp.feature.search.domain

import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature.search.data.repository.MostPopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface MostPopularMoviesUseCase {
    suspend operator fun invoke(): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse>
}

@Factory(binds = [_root_ide_package_.com.codandotv.streamplayerapp.feature.search.domain.MostPopularMoviesUseCase::class])
class MostPopularMoviesUseCaseImpl(
    val repository: com.codandotv.streamplayerapp.feature.search.data.repository.MostPopularMoviesRepository
) : com.codandotv.streamplayerapp.feature.search.domain.MostPopularMoviesUseCase {
    override suspend operator fun invoke(): Flow<com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse> {
        return repository.getMostPopularMovies()
    }
}
