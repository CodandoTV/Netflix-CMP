package com.codandotv.streamplayerapp.feature_search.domain

import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_search.data.repository.MostPopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface MostPopularMoviesUseCase {
    suspend operator fun invoke(): Flow<ListSearchStreamResponse>
}

@Factory(binds = [MostPopularMoviesUseCase::class])
class MostPopularMoviesUseCaseImpl(
    val repository: MostPopularMoviesRepository
) : MostPopularMoviesUseCase {
    override suspend operator fun invoke(): Flow<ListSearchStreamResponse> {
        return repository.getMostPopularMovies()
    }
}
