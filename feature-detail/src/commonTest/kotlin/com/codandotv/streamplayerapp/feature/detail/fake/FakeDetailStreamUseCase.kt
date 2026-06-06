package com.codandotv.streamplayerapp.feature.detail.fake

import com.codandotv.streamplayerapp.feature.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature.detail.fakeStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDetailStreamUseCase :
    DetailStreamUseCase {
    var getMovieCalled = false
    var lastToggledMovie: DetailStream? = null

    override suspend fun getMovie(): Flow<DetailStream> {
        getMovieCalled = true
        return flowOf(fakeStream)
    }

    override suspend fun toggleItemInFavorites(movie: DetailStream) {
        lastToggledMovie = movie
    }
}
