package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemContentType
import app.cash.paging.compose.itemKey
import kotlinx.coroutines.flow.Flow

@Composable
fun StreamsCarousel(
    content: StreamsCarouselContent,
    modifier: Modifier = Modifier,
    onNavigateDetailList: (String) -> Unit = {},
) {
    val lazyPagingItems = content.contentList.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()

    Column(modifier = modifier) {
        Text(
            content.genreTitle,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.size(8.dp))

        LazyRow(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            items(
                count = lazyPagingItems.itemCount,
                key = lazyPagingItems.itemKey(),
                contentType = lazyPagingItems.itemContentType()
            ) { index ->
                val item = lazyPagingItems[index]
                item?.let {
                    StreamsCard(
                        content = it,
                        onNavigateDetailList
                    )
                }
            }
        }
    }
}

data class StreamsCarouselContent(
    val genreTitle: String,
    val contentList: Flow<PagingData<StreamsCardContent>>
)
