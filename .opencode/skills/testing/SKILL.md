---
name: testing
description: Use when writing, modifying, or debugging tests — commonTest vs androidUnitTest, test dependencies, naming conventions, fake patterns, and test infrastructure for this KMP project.
---

# Testing Conventions

## Test Source Sets

| Source Set | Framework | Where to Use |
|---|---|---|
| `commonTest` | `kotlin.test` (`Test`, `BeforeTest`, `AfterTest`, `assertEquals`, `assertTrue`) | Shared business logic (use cases, repositories, view models, domain models) |
| `androidUnitTest` | JUnit4 + MockK + AndroidX Arch + Koin Test | Android-specific code (platform implementations, Android ViewModels with platform deps) |

## Test Dependencies (from `gradle/libs.versions.toml`)

| Bundle | Contents |
|---|---|
| `test_multiplatform` | `kotlin_test`, `kotlin_test_common`, `coroutines_test` |
| `test` | `junit`, `mockk`, `mockk_android`, `viewmodel_test` (`androidx.arch.core:core-testing`), `koin_test` (`koin-test-junit4`), `coroutines_test` |

To add tests to a module, include the appropriate bundle in its `build.gradle.kts`:
```kotlin
// commonTest
commonTest.dependencies {
    implementation(libs.bundles.test_multiplatform)
}

// androidUnitTest  
androidUnitTest.dependencies {
    implementation(libs.bundles.test)
}
```

## Existing Test Modules

| Module | Test Source Set | Files |
|---|---|---|
| `feature-detail` | `commonTest` | `DetailStreamViewModelTest.kt`, `DetailStreamUseCaseTest.kt`, `DetailStreamRepositoryTest.kt`, 5 fakes, `Shared.kt` |
| `feature-news` | `androidUnitTest` | No test files yet — bundle declared, source set ready |

## Test Patterns (from `feature-detail/commonTest`)

### 1. Hand-written Fakes (no mocking frameworks)

Fakes implement the production interface and track method calls with boolean flags:

```kotlin
class FakeDetailStreamRepository(
    private val movie: DetailStream
) : DetailStreamRepository {

    var getMovieCalled = false
    var deleteCalledWith: String? = null
    var insertCalledWith: DetailStream? = null

    override suspend fun getMovie(): Flow<DetailStream> {
        getMovieCalled = true
        return flowOf(movie)
    }

    override suspend fun deleteFromMyList(movie: String) {
        deleteCalledWith = movie
    }

    override suspend fun insertToMyList(movie: DetailStream) {
        insertCalledWith = movie
    }

    override suspend fun isFavorite(movieId: String): Boolean {
        isFavoriteCalledWith = movieId
        return false
    }

    override suspend fun getVideoStreams(): Flow<List<VideoStream>> {
        return flowOf(emptyList())
    }
}
```

**Fake conventions**:
- Package: `com.codandotv.streamplayerapp.{module}.fake`
- Name: `Fake{InterfaceName}`
- Tracks calls: `var {method}Called = false` (set to `true` when method is invoked)
- Tracks arguments: `var {param}CalledWith: Type? = null`
- Configurable return values via constructor parameters or public mutable properties
- Default return values are sensible defaults (empty lists, false, etc.)

### 2. Shared Test Data (`Shared.kt`)

```kotlin
package com.codandotv.streamplayerapp.feature.detail

val fakeStream = DetailStream(
    id = "1",
    title = "Fake Movie",
    overview = "Overview of the fake movie",
    tagline = "The ultimate test movie",
    url = "https://example.com/fake.jpg",
    releaseYear = "2025",
    isFavorite = false
)
```

### 3. Repository Test Pattern

```kotlin
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

### 4. Use Case Test Pattern

```kotlin
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

### 5. ViewModel Test Pattern

```kotlin
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

**ViewModel conventions**:
- Injects dependencies via constructor (not Koin — tests create view models directly)
- Sets `Dispatchers.setMain(StandardTestDispatcher())` in `@BeforeTest`
- Resets `Dispatchers.resetMain()` in `@AfterTest`
- Uses `advanceUntilIdle()` for coroutine completion
- Asserts UI state (`viewModel.uiState.value`) and side-effect flags

## When writing new tests

- Always use `kotlin.test.*` annotations in `commonTest` (not JUnit)
- Add fakes in the `fake` subpackage
- Add test data fixtures in a `Shared.kt` file at the package root
- Name test methods with backticks: `` fun `should do something`() ``
- Use `runTest` from `kotlinx.coroutines.test` for all coroutine-based code
- Assert both output values and side-effect tracking booleans
