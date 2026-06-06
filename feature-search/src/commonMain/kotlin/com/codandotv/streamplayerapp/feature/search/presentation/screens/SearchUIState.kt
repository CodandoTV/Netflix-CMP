package com.codandotv.streamplayerapp.feature.search.presentation.screens

import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse

sealed class SearchUIState {
    data class Success(val listCharacters: com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse) : com.codandotv.streamplayerapp.feature.search.presentation.screens.SearchUIState()
    data class Error(val messageError: String = "") : com.codandotv.streamplayerapp.feature.search.presentation.screens.SearchUIState()
    object Loading : com.codandotv.streamplayerapp.feature.search.presentation.screens.SearchUIState()
    object Empty : com.codandotv.streamplayerapp.feature.search.presentation.screens.SearchUIState()
}
