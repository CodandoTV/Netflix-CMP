package com.codandotv.streamplayerapp.feature.detail.domain

import com.codandotv.streamplayerapp.core_local_storage.domain.model.MovieEntity
import com.codandotv.streamplayerapp.core.shared.Url.IMAGE_URL_SIZE_500

fun com.codandotv.streamplayerapp.feature.detail.data.model.DetailStreamResponse.toDetailStream(isFavorite: Boolean = false): com.codandotv.streamplayerapp.feature.detail.domain.DetailStream =
    _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.domain.DetailStream(
        id = this.id.toString(),
        title = this.title,
        overview = this.overview,
        tagline = this.tagline,
        url = "$IMAGE_URL_SIZE_500${this.backdrop_path}",
        releaseYear = this.release_date.substring(0, 4),
        isFavorite = isFavorite
    )

fun com.codandotv.streamplayerapp.feature.detail.domain.DetailStream.toDetailStreamLocal(): MovieEntity =
    MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        tagline = this.tagline,
        url = this.url,
        releaseYear = this.releaseYear,
    )

fun com.codandotv.streamplayerapp.feature.detail.data.model.VideoStreamsResponse.toVideoStreams(): List<com.codandotv.streamplayerapp.feature.detail.domain.VideoStream> =
    results.map {
        _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.domain.VideoStream(
            videoId = it.key,
            movieId = this.id
        )
    }
