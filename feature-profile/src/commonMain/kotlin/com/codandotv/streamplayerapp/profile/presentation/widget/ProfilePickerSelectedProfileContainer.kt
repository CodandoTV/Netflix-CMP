package com.codandotv.streamplayerapp.profile.presentation.widget

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core_shared_ui.widget.WebImage
import com.codandotv.streamplayerapp.profile.presentation.screens.ProfilePickerStreamsUIState
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.feature_profile.generated.resources.Res
import streamplayerapp_kmp.feature_profile.generated.resources.profile_current_profile_name

@Suppress("MagicNumber")
@Composable
fun ProfilePickerSelectedProfileContainer(
    state: ProfilePickerStreamsUIState,
    offsetSelectedProfileImage: IntOffset,
    animatedSizeImage: Dp
) {
    with(state) {
        BoxWithConstraints(
            Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        ) {
            if (showCenterImage) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset { offsetSelectedProfileImage }
                ) {
                    selectedItem?.imageUrl?.let { imageUrl ->
                        WebImage(
                            imageUrl =imageUrl,
                            contentScale = ContentScale.Fit,
                            contentDescription = stringResource(
                                Res.string.profile_current_profile_name,
                                selectedItem.name
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(5))
                                .size(animatedSizeImage)
                                .alpha(centerImageAlpha)
                        )
                    }
                }
            }
        }
    }
}
