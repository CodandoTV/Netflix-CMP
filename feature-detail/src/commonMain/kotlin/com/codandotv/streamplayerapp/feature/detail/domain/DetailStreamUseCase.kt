package com.codandotv.streamplayerapp.feature.detail.domain

import com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepository
import kotlinx.coroutines.flow.Flow

interface DetailStreamUseCase {
    suspend fun getMovie(): Flow<com.codandotv.streamplayerapp.feature.detail.domain.DetailStream>

    suspend fun toggleItemInFavorites(movie: com.codandotv.streamplayerapp.feature.detail.domain.DetailStream)
}

class DetailStreamUseCaseImpl(
    private val detailStreamRepository: com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepository
) : com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCase {

    override suspend fun getMovie(): Flow<com.codandotv.streamplayerapp.feature.detail.domain.DetailStream> =
        detailStreamRepository.getMovie()

    override suspend fun toggleItemInFavorites(movie: com.codandotv.streamplayerapp.feature.detail.domain.DetailStream) {
        if (detailStreamRepository.isFavorite(movie.id)) {
            detailStreamRepository.deleteFromMyList(movie.id)
        } else {
            detailStreamRepository.insertToMyList(movie)
        }
    }
}
