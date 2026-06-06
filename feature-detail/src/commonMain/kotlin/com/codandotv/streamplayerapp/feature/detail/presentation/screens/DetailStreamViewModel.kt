package com.codandotv.streamplayerapp.feature.detail.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core.networking.handleError.catchFailure
import com.codandotv.streamplayerapp.core.networking.resources.StringNetworking
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature.detail.domain.VideoStreamsUseCase
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

class DetailStreamViewModel(
    private val detailStreamUseCase: DetailStreamUseCase,
    private val videoStreamsUseCase: VideoStreamsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        loadDetail()
    }

    private val _uiState = MutableStateFlow<DetailStreamsUIState>(
        DetailStreamsUIState.LoadingStreamUIState
    )
    val uiState: StateFlow<DetailStreamsUIState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    fun loadDetail() {
        viewModelScope.launch {
            detailStreamUseCase.getMovie()
                .zip(videoStreamsUseCase.getVideoStreams()) { detailStream, videoUrl ->
                    DetailStreamsUIState.DetailStreamsLoadedUIState(
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
        _uiState.update { DetailStreamsUIState.LoadingStreamUIState }
    }

    fun toggleItemInFavorites(detailStream: com.codandotv.streamplayerapp.feature.detail.domain.DetailStream) {
        viewModelScope.launch {
            detailStreamUseCase.toggleItemInFavorites(detailStream)
        }
    }
}
