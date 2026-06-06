package com.codandotv.streamplayerapp.feature.detail.domain

data class DetailStream(
    val id: String,
    val title: String,
    val overview: String,
    val tagline: String,
    val url: String,
    val releaseYear: String,
    val isFavorite: Boolean
)
