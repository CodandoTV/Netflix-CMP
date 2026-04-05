package com.codandotv.streamplayerapp.feature_detail.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.codandotv.streamplayerapp.core_shared_ui.widget.IconWithText
import com.codandotv.streamplayerapp.feature_detail.domain.DetailStream
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.feature_detail.generated.resources.Res
import streamplayerapp_kmp.feature_detail.generated.resources.detail_classification
import streamplayerapp_kmp.feature_detail.generated.resources.detail_download
import streamplayerapp_kmp.feature_detail.generated.resources.detail_my_list
import streamplayerapp_kmp.feature_detail.generated.resources.detail_share

@Composable
fun DetailStreamActionOption(
    detailStream: DetailStream,
    onToggleToMyList: (DetailStream) -> Unit,
    onShowSharingOptions: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var checked by rememberSaveable { mutableStateOf(detailStream.isFavorite) }
    var iconCheckList by remember { mutableStateOf(Icons.Filled.Add) }

    LaunchedEffect(checked) {
        iconCheckList =
            if (checked) Icons.Filled.Check else Icons.Filled.Add
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconWithText(
            onClick = {
                checked = !checked
                onToggleToMyList(detailStream)
            },
            imageVector = iconCheckList,
            imageColor = Color.White,
            text = stringResource(Res.string.detail_my_list),
            textColor = Color.Gray,
        )
        IconWithText(
            onClick = { TODO("Implementar mecanismo de classificação.") },
            imageVector = Icons.Filled.ThumbUp,
            imageColor = Color.White,
            text = stringResource(Res.string.detail_classification),
            textColor = Color.Gray,
        )
        IconWithText(
            onClick = { onShowSharingOptions.invoke() },
            imageVector = Icons.Filled.Share,
            imageColor = Color.White,
            text = stringResource(Res.string.detail_share),
            textColor = Color.Gray,
        )
        IconWithText(
            onClick = { TODO("Implementar mecanismo de download.") },
            imageVector = Icons.Filled.Share,
            imageColor = Color.White,
            text = stringResource(Res.string.detail_download),
            textColor = Color.Gray,
        )
    }
}
