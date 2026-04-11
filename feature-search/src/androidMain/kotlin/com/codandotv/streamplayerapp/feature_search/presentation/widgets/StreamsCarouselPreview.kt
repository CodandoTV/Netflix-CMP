package com.codandotv.streamplayerapp.feature_search.presentation.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun StreamsErrorPreview() {
    StreamsError(
        onRetry = {},
        onCloseButton = {}
    )
}

@Composable
@Preview
fun StreamEmptyPreview() {
    StreamsEmpty()
}
