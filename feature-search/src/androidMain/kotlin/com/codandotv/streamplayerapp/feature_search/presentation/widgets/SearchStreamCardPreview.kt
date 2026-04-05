package com.codandotv.streamplayerapp.feature_search.presentation.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews


@ThemePreviews
@Composable
fun SearchStreamCardPreview() {
    SearchStreamCard(
        content = SearchStreamCardModel(
            id = "1",
            title = "The Witcher",
            url = "https://image.tmdb.org/t/p/w200/iwsMu0ehRPbtaSxqiaUDQB9qMWT.jpg"
        ),
        onSearchStreamPressed = {}
    )
}

@ThemePreviews
@Composable
fun PlayerPreview() {
    PlayerIcon(modifier = Modifier)
}
