package com.codandotv.streamplayerapp.core.shared.ui.widget

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.core_shared_ui.generated.resources.Res
import streamplayerapp_kmp.core_shared_ui.generated.resources.icon_profile
import streamplayerapp_kmp.core_shared_ui.generated.resources.perfil_fake

@Composable
fun AvatarImageIcon(imageUrl: String?, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier
            .height(24.dp)
            .clip(RoundedCornerShape(4.dp)),
        model = imageUrl,
        error = painterResource(Res.drawable.perfil_fake),
        placeholder = painterResource(Res.drawable.perfil_fake),
        contentDescription = stringResource(Res.string.icon_profile)
    )
}