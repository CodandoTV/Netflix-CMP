package com.codandotv.streamplayerapp.feature.liststreams.presentation.widgets

import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core.shared.ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.IconAndTextInfo
import com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.HighlightBanner
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.core_shared_ui.generated.resources.Res
import streamplayerapp_kmp.core_shared_ui.generated.resources.app_name
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_add
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_info
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_play
import streamplayerapp_kmp.feature_list_streams.generated.resources.ic_top_10
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highlight_banner_add
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highlight_banner_info
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highlight_banner_watch

typealias ContentType = com.codandotv.streamplayerapp.feature.liststreams.core.ContentType
typealias HighlightBannerModel = HighlightBanner

@ThemePreviews
@Composable
fun HighlightBannerPreview() {
    HighlightBanner(
        data = HighlightBannerModel(
            name = stringResource(Res.string.app_name),
            imageUrl = String(),
            contentType = ContentType.getContentName(
                ContentType.SHOW
            ),
            contentTypeAsPlural = ContentType.getContentNameAsPlural(
                ContentType.SHOW
            ),
            extraInfo = IconAndTextInfo(
                Res.drawable.ic_top_10,
                ContentType.getContentName(
                    ContentType.SHOW
                )
            ),
            leftButton = IconAndTextInfo(
                Res.drawable.ic_add,
                Res.string.list_highlight_banner_add
            ),
            centralButton = IconAndTextInfo(
                Res.drawable.ic_play,
                Res.string.list_highlight_banner_watch
            ),
            rightButton = IconAndTextInfo(
                Res.drawable.ic_info,
                Res.string.list_highlight_banner_info
            ),
        )
    )
}
