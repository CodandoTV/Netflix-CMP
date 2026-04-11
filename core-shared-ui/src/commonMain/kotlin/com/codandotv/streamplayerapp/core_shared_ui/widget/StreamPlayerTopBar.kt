package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.core_shared_ui.generated.resources.Res
import streamplayerapp_kmp.core_shared_ui.generated.resources.ic_netflix
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_netflix
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_profile
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_search
import streamplayerapp_kmp.core_shared_ui.generated.resources.perfil_fake
import streamplayerapp_kmp.core_shared_ui.generated.resources.topbar_categories
import streamplayerapp_kmp.core_shared_ui.generated.resources.topbar_movies
import streamplayerapp_kmp.core_shared_ui.generated.resources.topbar_shows

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreamPlayerTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigateProfilePicker: () -> Unit = {},
    onNavigateSearchScreen: () -> Unit = {},
    onSelectedProfilePicture: String
) {
    Box(modifier = Modifier.background(color = Colors.Dark10)) {
        StreamPlayerTopBar(
            onNavigateProfilePicker = { onNavigateProfilePicker() },
            onNavigateSearchScreen = { onNavigateSearchScreen() },
            profilePicture = onSelectedProfilePicture
        )
        StreamPlayerOptionsTopBar(modifier = Modifier.padding(top = 50.dp), scrollBehavior)
    }
}

@Composable
private fun StreamPlayerTopBar(
    onNavigateProfilePicker: () -> Unit = {},
    onNavigateSearchScreen: () -> Unit = {},
    profilePicture: String
) {
    Row(
        modifier = Modifier
            .height(50.dp)
    ) {
        IconButton(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp),
            onClick = { /* todo */ }
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_netflix),
                contentDescription = stringResource(Res.string.icon_netflix),
                tint = Color.Unspecified,
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = {
                onNavigateSearchScreen.invoke()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(Res.string.icon_search),
                tint = Color.White,
            )
        }

        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = { onNavigateProfilePicker() }
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(24.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = profilePicture,
                error = painterResource(Res.drawable.perfil_fake),
                placeholder = painterResource(Res.drawable.perfil_fake),
                contentDescription = stringResource(Res.string.icon_profile)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StreamPlayerOptionsTopBar(modifier: Modifier, scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        modifier = modifier.padding(top = 4.dp),
        windowInsets = WindowInsets(top = 0.dp),
        title = {},
        scrollBehavior = scrollBehavior,
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                Text(
                    text = stringResource(Res.string.topbar_shows),
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )
                Text(
                    text = stringResource(Res.string.topbar_movies),
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )
                Text(
                    text = stringResource(Res.string.topbar_categories),
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}
