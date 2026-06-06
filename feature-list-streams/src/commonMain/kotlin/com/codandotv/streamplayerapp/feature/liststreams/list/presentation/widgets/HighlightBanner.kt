package com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codandotv.streamplayerapp.core_shared_ui.widget.WebImage
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.IconAndTextInfo
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_add
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_info
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_netflix
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_play
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_netflix
import streamplayerapp_kmp.feature_list_streams.generated.resources.Res
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highligh_banner_content
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_highlight_banner_watch
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_icon_add
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_icon_highligh_banner_ranking
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_icon_info
import streamplayerapp_kmp.feature_list_streams.generated.resources.list_icon_play
import streamplayerapp_kmp.core_shared_ui.generated.resources.Res as SharedRes

@Suppress("MagicNumber")
@Composable
fun HighlightBanner(modifier: Modifier = Modifier, data: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner?) {
    data ?: return

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.ContentImage(
            imageUrl = data.imageUrl
        )
        _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.BackgroundGradient()
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.ContentType(
                contentType = data.contentType
            )
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.ContentName(
                name = data.name
            )
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.ContentRanking(
                extraInfo = data.extraInfo,
                contentTypeAsPlural = data.contentTypeAsPlural
            )
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.ActionButtons(
                Modifier.weight(0.3F),
                data
            )
        }
    }
}

@Composable
fun ContentImage(modifier: Modifier = Modifier, imageUrl: String) {
    WebImage(
        modifier = modifier.fillMaxSize(),
        imageUrl = imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(Res.string.list_highligh_banner_content)
    )
}

@Composable
fun BackgroundGradient(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                )
            )
    )
}

@Composable
fun ContentName(modifier: Modifier = Modifier, name: String) {
    Text(
        text = name,
        fontSize = 24.sp,
        modifier = modifier
            .padding(horizontal = 50.dp)
            .padding(vertical = 4.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun ContentRanking(
    modifier: Modifier = Modifier,
    extraInfo: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.IconAndTextInfo,
    contentTypeAsPlural: StringResource
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(extraInfo.icon),
            contentDescription = stringResource(Res.string.list_icon_highligh_banner_ranking),
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(contentTypeAsPlural).lowercase(),
            Modifier.padding(start = 4.dp),
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ContentType(modifier: Modifier = Modifier, contentType: StringResource) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(SharedRes.drawable.ic_netflix),
            contentDescription = stringResource(SharedRes.string.icon_netflix),
            modifier = Modifier.size(16.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(contentType).uppercase(),
            Modifier.padding(start = 4.dp),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground,
            letterSpacing = 4.sp
        )
    }

}

@Composable
fun ActionButtons(modifier: Modifier, data: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.AddToListButton(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize(), data
        ) {
            /* todo */
        }
        _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.PlayButton {
            /* todo */
        }
        _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.widgets.InfoButton(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize(), data
        ) {
            /* todo */
        }
    }
}

@Composable
fun AddToListButton(
    modifier: Modifier = Modifier,
    data: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick.invoke() }, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(SharedRes.drawable.ic_add),
                contentDescription = stringResource(Res.string.list_icon_add),
                tint = Color.White,
            )
            Text(
                fontSize = 10.sp,
                text = stringResource(data.leftButton.text),
                color = Color.White,
            )
        }
    }
}

@Composable
fun InfoButton(
    modifier: Modifier = Modifier,
    data: com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.HighlightBanner,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick.invoke() }, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(SharedRes.drawable.ic_info),
                contentDescription = stringResource(Res.string.list_icon_info),
                tint = Color.White
            )
            Text(
                text = stringResource(data.rightButton.text),
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = modifier
            .padding(16.dp)
            .defaultMinSize(
                minWidth = 28.dp,
                minHeight = 28.dp
            )
    ) {
        Icon(
            painter = painterResource(SharedRes.drawable.ic_play),
            contentDescription = stringResource(Res.string.list_icon_play),
            tint = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(Res.string.list_highlight_banner_watch),
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(bottom = 2.dp)
                .padding(end = 8.dp)
        )
    }
}
