package com.codandotv.streamplayerapp.feature.search.domain.mapper

import com.codandotv.streamplayerapp.core_shared.Url
import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse.SearchStreamResponse
import com.codandotv.streamplayerapp.feature.search.presentation.widgets.SearchStreamCardModel

fun com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse.SearchStreamResponse.toSearchStreamCardModel() =
    _root_ide_package_.com.codandotv.streamplayerapp.feature.search.presentation.widgets.SearchStreamCardModel(
        id = id.toString(),
        title = title,
        url = "${Url.IMAGE_URL_SIZE_200}${posterPath}"
    )
