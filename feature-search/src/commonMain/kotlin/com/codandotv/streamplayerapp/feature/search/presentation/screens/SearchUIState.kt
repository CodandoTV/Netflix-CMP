package com.codandotv.streamplayerapp.feature.search.presentation.screens

import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse

sealed class SearchUIState(
    open val profilePictureUrl: String?
) {

    data class Success(
        val listCharacters: ListSearchStreamResponse,
        override val profilePictureUrl: String?
    ) : SearchUIState(profilePictureUrl)

    data class Error(
        val messageError: String = "",
        override val profilePictureUrl: String?
    ) : SearchUIState(profilePictureUrl)

    object Loading : SearchUIState(null)
    object Empty : SearchUIState(null)
}
