package com.codandotv.streamplayerapp.core_shared_ui.widget

import android.os.Parcelable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codandotv.streamplayerapp.core_shared.Url.IMAGE_URL_SIZE_300
import kotlinx.parcelize.Parcelize

@Composable
fun StreamsCard(
    content: StreamsCardContent,
    onNavigateDetailList: (String) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .size(
                width = 100.dp,
                height = 140.dp
            )
            .padding(
                horizontal = 4.dp
            )
            .clickable {
                onNavigateDetailList.invoke(content.id)
            }
    ) {
        AsyncImage(
            model = content.url,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = content.contentDescription
        )
    }
}

@Parcelize
data class StreamsCardContent(
    val id: String,
    val url: String,
    val contentDescription: String,
) : Parcelable
