package com.codandotv.streamplayerapp.feature.search.presentation.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun SearchBarPreview() {
    StreamPlayerTopBar(
        onBackPressed = {}
    )
}

@Composable
@Preview
fun SearchTopBarEmptyPreview() {
    SearchTopBar(
        currentSearchText = "",
        onSearchTextChanged = {},
        onSearchDispatched = {},
        onCleanTextPressed = {},
        onSearchIconPressed = {}
    )
}

@Composable
@Preview
fun SearchTopBarPreview() {
    SearchTopBar(
        currentSearchText = "Texto de busca",
        onSearchTextChanged = {},
        onSearchDispatched = {},
        onCleanTextPressed = {},
        onSearchIconPressed = {}
    )
}
