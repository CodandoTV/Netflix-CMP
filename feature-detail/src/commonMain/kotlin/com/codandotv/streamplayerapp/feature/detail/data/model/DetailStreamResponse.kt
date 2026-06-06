package com.codandotv.streamplayerapp.feature.detail.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Suppress("ConstructorParameterNaming")
data class DetailStreamResponse(
    val id: Long,
    val title: String,
    val overview: String,
    val tagline: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("release_date")
    val releaseDate: String
)
