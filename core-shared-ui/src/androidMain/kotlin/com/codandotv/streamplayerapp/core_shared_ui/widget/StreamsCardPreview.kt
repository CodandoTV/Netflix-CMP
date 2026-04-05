package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StreamsCardPreview() {
    StreamsCard(
        StreamsCardContent(
            url = "https://image.tmdb.org/t/p/w300/evgwd37VHBJhXvSr88Mrx5riFil.jpg",
            contentDescription = "Test 1",
            id = "",
        )
    )
}
