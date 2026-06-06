package com.codandotv.streamplayerapp.feature.detail.data

import com.codandotv.streamplayerapp.core_local_storage.data.dao.FavoriteDao
import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature.detail.domain.VideoStream
import com.codandotv.streamplayerapp.feature.detail.domain.toDetailStream
import com.codandotv.streamplayerapp.feature.detail.domain.toDetailStreamLocal
import com.codandotv.streamplayerapp.feature.detail.domain.toVideoStreams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DetailStreamRepository {
    suspend fun getMovie(): Flow<com.codandotv.streamplayerapp.feature.detail.domain.DetailStream>
    suspend fun deleteFromMyList(movie: String)
    suspend fun insertToMyList(movie: com.codandotv.streamplayerapp.feature.detail.domain.DetailStream)
    suspend fun isFavorite(movieId:String) : Boolean
    suspend fun getVideoStreams(): Flow<List<com.codandotv.streamplayerapp.feature.detail.domain.VideoStream>>
}

class DetailStreamRepositoryImpl(
    private val movieId: String,
    private val service: com.codandotv.streamplayerapp.feature.detail.data.DetailStreamService,
    private val favoriteDao: FavoriteDao,
) : com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepository {

    override suspend fun getMovie(): Flow<com.codandotv.streamplayerapp.feature.detail.domain.DetailStream> =
        service.getMovie(movieId)
            .toFlow()
            .map {
                it.toDetailStream(isFavorite(movieId))
            }


    override suspend fun deleteFromMyList(movie: String) = favoriteDao.delete(movie)

    override suspend fun insertToMyList(movie: com.codandotv.streamplayerapp.feature.detail.domain.DetailStream) = favoriteDao.insert(movie.toDetailStreamLocal())

    /**
     * Verify if movieId was saved as favorite
     * @param movieId
     * @return Boolean
     */
    override suspend fun isFavorite(movieId: String) : Boolean = favoriteDao.fetchAll().any {
        movie -> movie.id == movieId
    }
    
    override suspend fun getVideoStreams(): Flow<List<com.codandotv.streamplayerapp.feature.detail.domain.VideoStream>> =
        service.getVideoStreams(movieId)
            .toFlow()
            .map {
                it.toVideoStreams()
            }
}
