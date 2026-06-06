package com.codandotv.streamplayerapp.feature.liststreams.list.domain

import ListStreamResponse
import StreamResponse
import com.codandotv.streamplayerapp.core.shared.Url

fun ListStreamResponse.toListStream(genre: String): com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.ListStream =
    _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.ListStream(
        categoryName = genre,
        streams = this.results.map { streamResponse ->
            streamResponse.toStream()
        }
    )

fun com.codandotv.streamplayerapp.feature.liststreams.list.data.model.GenresResponse.toGenres(): List<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre> = this.genres.map { genreResponse ->
    _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre(
        id = genreResponse.id,
        name = genreResponse.name
    )
}

fun StreamResponse.toStream(): com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream =
    _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream(
        description = overview,
        name = title,
        posterPathUrl = "${Url.IMAGE_URL_SIZE_300}${poster_path}",
        id = id.toString()
    )
