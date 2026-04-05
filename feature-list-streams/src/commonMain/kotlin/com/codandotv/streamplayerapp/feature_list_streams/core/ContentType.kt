package com.codandotv.streamplayerapp.feature_list_streams.core

import org.jetbrains.compose.resources.StringResource
import streamplayerapp_kmp.feature_list_streams.generated.resources.Res
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_content_type_film
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_content_type_film_plural
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_content_type_show
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_content_type_show_plural

enum class ContentType(val contentName: StringResource, val contentNameAsPlural: StringResource) {
    SHOW(Res.string.list_content_type_show, Res.string.list_content_type_show_plural),
    FILM(Res.string.list_content_type_film, Res.string.list_content_type_film_plural);

    companion object {
        fun getContentName(contentType: ContentType) =
            entries.first { contentType == it }.contentName

        fun getContentNameAsPlural(contentType: ContentType) =
            entries.first { contentType == it }.contentNameAsPlural
    }
}
