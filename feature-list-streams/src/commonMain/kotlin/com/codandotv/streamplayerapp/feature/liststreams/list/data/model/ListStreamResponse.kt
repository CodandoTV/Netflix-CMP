package com.codandotv.streamplayerapp.feature.liststreams.list.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String? = null
)

@Serializable
data class ListStreamResponse(
    val results: List<StreamResponse>
)
