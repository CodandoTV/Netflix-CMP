package com.codandotv.streamplayerapp.feature.search.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListSearchStreamResponse(
    @SerialName("results")
    val results: List<SearchStreamResponse>
) {
    @Serializable
    data class SearchStreamResponse(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("overview")
        val overview: String,
        @SerialName("poster_path")
        val posterPath: String? = null
    )
}
