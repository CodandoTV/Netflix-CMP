package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.core.ContentType
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.GetGenresUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.GetTopRatedStream
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Genre
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.IconAndTextInfo
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Stream
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamsCardContent
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamsCarouselContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_add
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_info
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_play
import streamplayerapp_kmp.feature_list_streams.generated.resources.Res
import streamplayerapp_kmp.feature_list_streams.generated.resources.ic_top_10
import streamplayerapp_kmp.core_shared_ui.generated.resources.Res as SharedRes
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highlight_banner_add
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highlight_banner_info
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highlight_banner_stream_ranking
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highlight_banner_watch

class ListStreamViewModel(
    private val listStreams: ListStreamUseCase,
    private val listGenres: GetGenresUseCase,
    private val latestStream: GetTopRatedStream
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ListStreamsUIState(
            streamsCarouselContent = emptyList(),
            isLoading = false
        )
    )
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    init {
        viewModelScope.launch {
            latestStream()
                .combine(
                    listGenres()
                ) { latest, genres ->
                    Pair(latest, genres)
                }
                .onStart {
                    onLoading()
                }
                .onCompletion { loaded() }
                .catchFailure {
                    println(">>>> ${it.errorMessage}")
                }
                .collect { pair ->
                    val (latest, genres) = pair
                    _uiState.update {
                        it.copy(
                            streamsCarouselContent = genres.map { genreTarget ->
                                getStreamsByGenre(genreTarget)
                            },
                            highlightBanner = getHighlightBanner(latest)
                        )
                    }
                }
        }
    }

    private fun getHighlightBanner(latest: Stream) =
        HighlightBanner(
            name = latest.name,
            imageUrl = latest.posterPathUrl,
            contentType = ContentType.getContentName(ContentType.FILM),
            contentTypeAsPlural = ContentType.getContentNameAsPlural(ContentType.FILM),
            extraInfo = IconAndTextInfo(
                Res.drawable.ic_top_10,
                Res.string.list_highlight_banner_stream_ranking
            ),
            leftButton = IconAndTextInfo(
                SharedRes.drawable.ic_add,
                Res.string.list_highlight_banner_add
            ),
            centralButton = IconAndTextInfo(
                SharedRes.drawable.ic_play,
                Res.string.list_highlight_banner_watch
            ),
            rightButton = IconAndTextInfo(
                SharedRes.drawable.ic_info,
                Res.string.list_highlight_banner_info
            ),
        )


    private fun getStreamsByGenre(genre: Genre): StreamsCarouselContent {
        return StreamsCarouselContent(
            genre.name,
            listStreams(genre).map {
                it.map { stream ->
                    StreamsCardContent(
                        contentDescription = stream.name,
                        url = stream.posterPathUrl,
                        id = stream.id
                    )
                }
            }.cachedIn(viewModelScope)
        )
    }

    private fun loaded() {
        this._uiState.update {
            it.copy(isLoading = false)
        }
    }

    private fun onLoading() {
        this._uiState.update {
            it.copy(isLoading = true)
        }
    }
}
