package com.codandotv.streamplayerapp.feature.detail.domain

import kotlinx.coroutines.flow.Flow

interface DetailStreamRepository {
    suspend fun getMovie(): Flow<DetailStream>
    suspend fun deleteFromMyList(movie: String)
    suspend fun insertToMyList(movie: DetailStream)
    suspend fun isFavorite(movieId: String): Boolean
    suspend fun getVideoStreams(): Flow<List<VideoStream>>
}
