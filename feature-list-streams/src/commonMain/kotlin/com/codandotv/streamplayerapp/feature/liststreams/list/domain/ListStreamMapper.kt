package com.codandotv.streamplayerapp.feature.liststreams.list.domain

import com.codandotv.streamplayerapp.core.shared.Url
import com.codandotv.streamplayerapp.feature.liststreams.list.data.model.GenresResponse
import com.codandotv.streamplayerapp.feature.liststreams.list.data.model.ListStreamResponse
import com.codandotv.streamplayerapp.feature.liststreams.list.data.model.StreamResponse
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Genre
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.ListStream
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream

fun ListStreamResponse.toListStream(genre: String): ListStream =
    ListStream(
        categoryName = genre,
        streams = this.results.map { streamResponse ->
            streamResponse.toStream()
        }
    )

fun GenresResponse.toGenres(): List<Genre> = this.genres.map { genreResponse ->
    Genre(
        id = genreResponse.id,
        name = genreResponse.name
    )
}

fun StreamResponse.toStream(): Stream =
    Stream(
        description = overview,
        name = title,
        posterPathUrl = "${Url.IMAGE_URL_SIZE_300}$posterPath",
        id = id.toString()
    )
