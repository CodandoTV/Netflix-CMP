package com.codandotv.streamplayerapp.feature.detail.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.core_networking.resources.StringNetworking
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature.detail.domain.VideoStreamsUseCase
import com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState.DetailStreamsLoadedUIState
import com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState.LoadingStreamUIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class  DetailStreamViewModel(
    private val detailStreamUseCase: com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCase,
    private val videoStreamsUseCase: com.codandotv.streamplayerapp.feature.detail.domain.VideoStreamsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        loadDetail()
    }

    private val _uiState = MutableStateFlow<com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState>(
        _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState.LoadingStreamUIState
    )
    val uiState: StateFlow<com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    fun loadDetail() {
        viewModelScope.launch {
            detailStreamUseCase.getMovie()
                .zip(videoStreamsUseCase.getVideoStreams()) { detailStream, videoUrl ->
                    _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState.DetailStreamsLoadedUIState(
                        detailStream = detailStream,
                        videoId = videoUrl.firstOrNull()?.videoId
                    )
                }
                .flowOn(dispatcher)
                .onStart { onLoading() }
                .catchFailure {
                    println(">>>> ${StringNetworking.getStringResource(it.errorMessageResKey)}")
                }
                .collect { result ->
                    _uiState.update {
                        result
                    }
                }
        }
    }

    private fun onLoading() {
        _uiState.update { _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamsUIState.LoadingStreamUIState }
    }

    fun toggleItemInFavorites(detailStream: com.codandotv.streamplayerapp.feature.detail.domain.DetailStream) {
        viewModelScope.launch {
            detailStreamUseCase.toggleItemInFavorites(detailStream)
        }
    }
}
