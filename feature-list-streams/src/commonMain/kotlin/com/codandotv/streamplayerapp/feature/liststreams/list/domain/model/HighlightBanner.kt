package com.codandotv.streamplayerapp.feature.liststreams.list.domain.model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class HighlightBanner(
    val name: String,
    val imageUrl: String,
    val contentType: StringResource,
    val contentTypeAsPlural: StringResource,
    val extraInfo: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.IconAndTextInfo,
    val leftButton: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.IconAndTextInfo,
    val centralButton: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.IconAndTextInfo,
    val rightButton: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.IconAndTextInfo
)

data class IconAndTextInfo(
    val icon: DrawableResource,
    val text: StringResource
)
