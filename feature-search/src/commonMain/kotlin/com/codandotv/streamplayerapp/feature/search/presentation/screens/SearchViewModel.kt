package com.codandotv.streamplayerapp.feature.search.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core.networking.handleError.catchFailure
import com.codandotv.streamplayerapp.core.session.domain.SessionManager
import com.codandotv.streamplayerapp.feature.search.domain.MostPopularMoviesUseCase
import com.codandotv.streamplayerapp.feature.search.domain.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val mostPopularMoviesUseCase: MostPopularMoviesUseCase,
    @Provided private val sessionManager: SessionManager
) : ViewModel() {

    private var tryAgain: () -> Unit = {}

    private val _uiState = MutableStateFlow<SearchUIState>(
        SearchUIState.Loading
    )
    val uiState: StateFlow<SearchUIState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    private val _currentSearchText = MutableStateFlow("")
    val currentSearchText = _currentSearchText.asStateFlow()

    init {
        fetchMostPopularMovies()
    }

    fun fetchMovies() {
        if (_currentSearchText.value.isBlank()) {
            fetchMostPopularMovies()
        } else {
            fetchMovieByQuery()
        }
    }

    private fun userProfilePictureUrl() = sessionManager.userSessionInfo()?.profileImageUrl

    private fun fetchMovieByQuery() {
        tryAgain = ::fetchMovieByQuery

        viewModelScope.launch {
            searchUseCase(
                query = _currentSearchText.value
            ).onStart {
                showLoading()
            }.catchFailure { response ->
                showError(messageError = response.errorMessage.orEmpty())
            }.collect { result ->
                _uiState.update {
                    if (result.results.isEmpty()) {
                        SearchUIState.Empty
                    } else {
                        SearchUIState.Success(
                            profilePictureUrl = userProfilePictureUrl(),
                            listCharacters = result
                        )
                    }
                }
            }
        }
    }

    private fun fetchMostPopularMovies() {
        tryAgain = ::fetchMostPopularMovies

        viewModelScope.launch {
            mostPopularMoviesUseCase().onStart {
                showLoading()
            }.catchFailure { response ->
                showError(messageError = response.errorMessage.orEmpty())
            }.collect { result ->
                _uiState.update {
                    if (result.results.isEmpty()) {
                        SearchUIState.Empty
                    } else {
                        SearchUIState.Success(
                            profilePictureUrl = userProfilePictureUrl(),
                            listCharacters = result
                        )
                    }
                }
            }
        }
    }

    private fun showError(messageError: String) {
        _uiState.update {
            SearchUIState.Error(
                messageError = messageError,
                profilePictureUrl = userProfilePictureUrl(),
            )
        }
    }

    private fun showLoading() {
        _uiState.update {
            SearchUIState.Loading
        }
    }

    fun onTryAgain() {
        tryAgain()
    }

    fun setCurrentSearchText(newText: String) {
        _currentSearchText.value = newText
    }

    fun onCleanText() {
        _currentSearchText.value = ""
        fetchMostPopularMovies()
    }
}
