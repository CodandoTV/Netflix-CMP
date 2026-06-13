---
name: unit-test-generator
description: Use ONLY when the user asks to generate, write, or create unit tests. Do NOT use for test infrastructure questions or debugging tests. This skill generates tests for only the files changed between the current branch and main.
---

# Unit Test Generator

## Step 1: Identify changed production files

Run `git diff main...HEAD --name-only` to find modified files. Filter to only the source directories (not test files, not build files, not config files):

```bash
git diff main...HEAD --name-only | grep 'src/commonMain\|src/androidMain\|src/iosMain'
```

## Step 2: For each changed file, determine the test type

### File → Test mapping

| Source file pattern | Test to generate | Fakes needed |
|---|---|---|
| `.../data/*ServiceImpl.kt` or `.../data/*Service.kt` with interface | Service/API layer | Skip — service tests not yet established, focus on repos + use cases + VMs |
| `.../data/*RepositoryImpl.kt` or `.../data/*Repository.kt` with interface | `*RepositoryTest.kt` in `commonTest` | Fake{Service}, Fake{Dao} for each dependency |
| `.../domain/*UseCaseImpl.kt` or `.../domain/*UseCase.kt` with interface | `*UseCaseTest.kt` in `commonTest` | Fake{Repository} |
| `.../presentation/*ViewModel.kt` | `*ViewModelTest.kt` in `commonTest` | Fake{UseCase} for each use case dependency |
| Any other file | Skip — only generate for repo/use case/ViewModel layers |

### Where to create the test files

- If the source is in `commonMain` → test goes in `commonTest` with the same package path
- If the source is in `androidMain` → test goes in `androidUnitTest` with the same package path
- If the source is in `iosMain` → no test generated (no iosTest source set exists)

Check the module's `build.gradle.kts` to confirm the test source set is configured before generating.

## Step 3: Generate fakes (one per dependency interface)

### Fake conventions

Place fakes in a `fake` subpackage under the test root:
```
src/commonTest/kotlin/com/codandotv/streamplayerapp/{module}/fake/Fake{InterfaceName}.kt
```

**Pattern**: implement the interface, track calls with boolean flags, configurable return values.

```kotlin
package com.codandotv.streamplayerapp.feature.detail.fake

import com.codandotv.streamplayerapp.feature.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature.detail.fakeStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDetailStreamUseCase : DetailStreamUseCase {
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
```

**For suspend functions returning `Flow`**: use `flowOf(...)` with a configurable value.
**For suspend functions returning a value directly**: configurable via constructor or public var.
**For interfaces with `NetworkResponse`**: use `NetworkResponse.Success(...)` as default.

## Step 4: Generate or update Shared.kt

If the module doesn't have a `Shared.kt` yet, create one at the package root of the test source set:
```
src/commonTest/kotlin/com/codandotv/streamplayerapp/{module}/Shared.kt
```

Fill it with test fixture data objects used across multiple test files.

## Step 5: Generate the test class

### Repository test pattern

```kotlin
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
        repository = DetailStreamRepositoryImpl(
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
```

### Use case test pattern

```kotlin
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
```

### ViewModel test pattern

```kotlin
package com.codandotv.streamplayerapp.feature.detail.presentation

import com.codandotv.streamplayerapp.feature.detail.expectedDetailStreamLoadedUI
import com.codandotv.streamplayerapp.feature.detail.fake.FakeDetailStreamUseCase
import com.codandotv.streamplayerapp.feature.detail.fake.FakeVideoStreamsUseCase
import com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DetailStreamViewModelTest {

    private lateinit var detailStreamViewModel: DetailStreamViewModel
    private lateinit var detailUseCase: FakeDetailStreamUseCase
    private lateinit var videoUseCase: FakeVideoStreamsUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        detailUseCase = FakeDetailStreamUseCase()
        videoUseCase = FakeVideoStreamsUseCase()
        detailStreamViewModel = DetailStreamViewModel(
            detailStreamUseCase = detailUseCase,
            videoStreamsUseCase = videoUseCase,
            dispatcher = StandardTestDispatcher()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should load the movies with videoId`() = runTest {
        detailStreamViewModel.loadDetail()
        advanceUntilIdle()
        assertEquals(expectedDetailStreamLoadedUI, detailStreamViewModel.uiState.value)
        assertEquals(true, detailUseCase.getMovieCalled)
        assertEquals(true, videoUseCase.getVideoStreamsCalled)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun after() {
        Dispatchers.resetMain()
    }
}
```

## Test method guidelines

- Name tests with backtick strings: `` fun `should do something when condition`() ``
- Use `kotlin.test.Test`, `kotlin.test.BeforeTest`, `kotlin.test.AfterTest` (NOT JUnit annotations)
- Use `kotlin.test.assertEquals`, `kotlin.test.assertTrue`, `kotlin.test.assertFalse`
- Wrap coroutine tests with `runTest { ... }` from `kotlinx.coroutines.test`
- For ViewModel tests: set `Dispatchers.setMain(StandardTestDispatcher())` in `@BeforeTest` and `Dispatchers.resetMain()` in `@AfterTest`
- Use `advanceUntilIdle()` to complete all coroutines in the test dispatcher
- Assert both the output result AND the side-effect tracking booleans on fakes

## Step 6: Verify

After generating tests, run:

```bash
./gradlew :{module}:compileTestKotlinMetadata  # for commonTest
# OR
./gradlew :{module}:compileDebugUnitTestKotlin  # for androidUnitTest
```

If the module uses Koin annotations, also run:
```bash
./gradlew :{module}:kspCommonMainKotlinMetadata
```
