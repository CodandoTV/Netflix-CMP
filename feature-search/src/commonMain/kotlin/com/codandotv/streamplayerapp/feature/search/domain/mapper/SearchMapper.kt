package com.codandotv.streamplayerapp.feature.search.domain.mapper

import com.codandotv.streamplayerapp.core.shared.Url
import com.codandotv.streamplayerapp.feature.search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature.search.presentation.widgets.SearchStreamCardModel

fun ListSearchStreamResponse.SearchStreamResponse.toSearchStreamCardModel() =
    SearchStreamCardModel(
        id = id.toString(),
        title = title,
        url = "${Url.IMAGE_URL_SIZE_200}$posterPath"
    )
