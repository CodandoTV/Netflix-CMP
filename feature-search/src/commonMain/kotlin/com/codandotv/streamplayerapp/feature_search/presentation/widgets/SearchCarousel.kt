package com.codandotv.streamplayerapp.feature_search.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamsCard
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamsCardContent
import com.codandotv.streamplayerapp.feature.search.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.feature_search.generated.resources.Res
import streamplayerapp_kmp.feature_search.generated.resources.bottom_search_list_error
import streamplayerapp_kmp.feature_search.generated.resources.empty_search_list
import streamplayerapp_kmp.feature_search.generated.resources.search_back
import streamplayerapp_kmp.feature_search.generated.resources.search_list_describle
import streamplayerapp_kmp.feature_search.generated.resources.search_list_error

data class SearchCarousel(
    val genreTitle: String,
    val contentList: Flow<PagingData<StreamsCardContent>>
)

@Composable
fun SearchCarouselStream(
    content: SearchCarousel,
    onNavigateDetailList: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = content.contentList.collectAsLazyPagingItems()

    Text(
        text = stringResource(Res.string.search_list_describle),
        color = Color.Black,
        fontSize = 14.sp
    )

    Column {
        LazyColumn(
            modifier
                .fillMaxSize()
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
                        onNavigateDetailList = onNavigateDetailList
                    )
                }
            }
        }
    }
}

@Composable
fun StreamsError(
    onRetry: () -> Unit,
    onCloseButton: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = {
            onCloseButton()
        }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(Res.string.search_back),
                tint = Color.White
            )
        }
        Text(
            text = stringResource(Res.string.search_list_error),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            textAlign= TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRetry,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.BottomCenter),
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Red
            ),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = stringResource(Res.string.bottom_search_list_error))
        }
    }
}

@Composable
fun StreamsEmpty() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(
                text = stringResource(Res.string.empty_search_list),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)

            )
        }
    }
}
