package com.codandotv.streamplayerapp.feature.search.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core.shared.ui.resources.Colors
import com.codandotv.streamplayerapp.core.shared.ui.widget.CloseButton
import com.codandotv.streamplayerapp.core.shared.ui.widget.MicButton
import com.codandotv.streamplayerapp.core.shared.ui.widget.SearchIcon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_back
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_cast
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_profile
import streamplayerapp_kmp.core_shared_ui.generated.resources.perfil_fake
import streamplayerapp_kmp.feature_search.generated.resources.Res
import streamplayerapp_kmp.feature_search.generated.resources.search_list_main_search
import streamplayerapp_kmp.core_shared_ui.generated.resources.Res as SharedRes

@Suppress("LongParameterList")
@Composable
fun SearchableTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDispatched: () -> Unit,
    onSearchIconPressed: () -> Unit,
    onBackPressed: () -> Unit,
    onCleanTextPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        StreamPlayerTopBar(
            onBackPressed = onBackPressed
        )
        SearchTopBar(
            currentSearchText = currentSearchText,
            onSearchTextChanged = onSearchTextChanged,
            onSearchDispatched = onSearchDispatched,
            onCleanTextPressed = onCleanTextPressed,
            onSearchIconPressed = onSearchIconPressed
        )
    }
}

@Composable
internal fun StreamPlayerTopBar(
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(56.dp)
    ) {
        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = {
                onBackPressed()
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(SharedRes.string.icon_back),
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(SharedRes.string.icon_cast),
                tint = Color.White,
            )
        }

        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = { /* todo */ }
        ) {
            Icon(
                modifier = Modifier
                    .height(24.dp)
                    .clip(RoundedCornerShape(4.dp)),
                painter = painterResource(SharedRes.drawable.perfil_fake),
                contentDescription = stringResource(SharedRes.string.icon_profile),
                tint = Color.Unspecified,
            )
        }
    }
}

@Composable
fun SearchTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDispatched: () -> Unit,
    onCleanTextPressed: () -> Unit,
    onSearchIconPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Colors.Gray100)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentSearchText,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Colors.Gray100,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray
            ),
            onValueChange = {
                onSearchTextChanged(it)
            },
            placeholder = {
                Text(
                    text = stringResource(Res.string.search_list_main_search),
                    color = Color.Gray
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = Color.White
            ),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                SearchIcon(
                    action = onSearchIconPressed
                )
            },
            trailingIcon = {
                if (currentSearchText.isEmpty()) {
                    MicButton()
                } else {
                    CloseButton(
                        action = onCleanTextPressed
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchDispatched()
            }),
        )
    }
}
