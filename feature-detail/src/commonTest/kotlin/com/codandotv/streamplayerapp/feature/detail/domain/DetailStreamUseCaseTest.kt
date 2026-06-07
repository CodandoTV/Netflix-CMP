package com.codandotv.streamplayerapp.feature.detail.domain

import FakeDetailStreamRepository
import com.codandotv.streamplayerapp.feature.detail.fakeStream
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DetailStreamUseCaseTest {
    private lateinit var detailStreamUseCase: DetailStreamUseCase
    private lateinit var detailStreamRepository: FakeDetailStreamRepository

    @BeforeTest
    fun setUp() {
        detailStreamRepository = FakeDetailStreamRepository(movie = fakeStream)
        detailStreamUseCase = DetailStreamUseCaseImpl(
            detailStreamRepository = detailStreamRepository
        )
    }

    @Test
    fun `load movies`() = runTest {
        var collected: DetailStream? = null

        detailStreamUseCase.getMovie().collect {
            collected = it
        }

        assertEquals(fakeStream, collected)
        assertTrue(detailStreamRepository.getMovieCalled)
    }
}