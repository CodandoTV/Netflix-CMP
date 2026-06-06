package com.codandotv.streamplayerapp.feature.detail.data

import com.codandotv.streamplayerapp.core.networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature.detail.domain.toDetailStream
import com.codandotv.streamplayerapp.feature.detail.expectedDetailStream
import com.codandotv.streamplayerapp.feature.detail.fake.FakeDetailStreamService
import com.codandotv.streamplayerapp.feature.detail.fake.FakeFavoriteDao
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DetailStreamRepositoryTest {
    private lateinit var repository: DetailStreamRepository
    private val movieId = "123"
    private lateinit var service: FakeDetailStreamService
    private lateinit var favoriteDao: FakeFavoriteDao

    @BeforeTest
    fun setUp() {
        service = FakeDetailStreamService()
        favoriteDao = FakeFavoriteDao()
        repository =
            _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepositoryImpl(
                movieId = movieId,
                service = service,
                favoriteDao = favoriteDao
            )
    }

    @Test
    fun `getMovie should load the movie when passed a movieId`() = runTest {
        service.movieResponse = NetworkResponse.Success(expectedDetailStream)
        var collected = false

        repository.getMovie().collect { result ->
            collected = true
            assertEquals(expectedDetailStream.toDetailStream(), result)
        }

        assertTrue(collected, "Expected flow to emit a value")
        assertTrue(service.getMovieCalled, "Service should have been called")
        assertTrue(favoriteDao.fetchAllCalled, "FavoriteDao should have been called")
    }
}