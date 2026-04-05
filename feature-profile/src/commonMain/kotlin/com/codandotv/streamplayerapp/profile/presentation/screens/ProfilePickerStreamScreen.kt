package com.codandotv.streamplayerapp.profile.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.profile.domain.ProfileStream
import com.codandotv.streamplayerapp.profile.presentation.widget.LoadScreen
import com.codandotv.streamplayerapp.profile.presentation.widget.ProfilePickerOpacityLayer
import com.codandotv.streamplayerapp.profile.presentation.widget.ProfilePickerProfilesGrid
import com.codandotv.streamplayerapp.profile.presentation.widget.ProfilePickerSelectedProfileContainer
import com.codandotv.streamplayerapp.profile.presentation.widget.ProfilePickerStreamToolbar
import com.codandotv.streamplayerapp.profile.presentation.widget.dpToPx
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import streamplayerapp_kmp.feature_profile.generated.resources.Res
import streamplayerapp_kmp.feature_profile.generated.resources.profile_animation_background_opacity
import streamplayerapp_kmp.feature_profile.generated.resources.profile_animation_selected_image_position
import streamplayerapp_kmp.feature_profile.generated.resources.profile_animation_selected_image_size
import streamplayerapp_kmp.feature_profile.generated.resources.profile_animation_showing_all_profiles

@Composable
fun ProfilePickerStreamScreen(
    viewModel: ProfilePickerStreamViewModel = koinViewModel(),
    onNavigateListStreams: (String) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        LoadScreen()
    } else {
        SetupProfilePickerScreen(
            uiState = uiState,
            onSetCenterImageAlpha = { viewModel.setCenterImageAlpha(it) },
            onSetLastItemPositioned = { viewModel.setLastItemPositioned(it) },
            onSetHaltSizeImage = { viewModel.setHaltSizeImage(it) },
            onSetHalfExpandedSizeImage = { viewModel.setHalfExpandedSizeImage(it) },
            onClickSelectedProfile = { viewModel.moveSelectedProfileToCenterImage(it) },
            onSetScreenSize = { width, height, widthPx, heightPx ->
                viewModel.setScreenSize(width, height, widthPx, heightPx)
            },
            onNavigateListStreams = onNavigateListStreams,
        )
    }
}

@Suppress("LongMethod", "LongParameterList")
@Composable
fun SetupProfilePickerScreen(
    uiState: ProfilePickerStreamsUIState,
    onSetCenterImageAlpha: (Float) -> Unit = {},
    onSetScreenSize: (Float, Float, Int, Int) -> Unit = { _, _, _, _ -> },
    onSetLastItemPositioned: (Boolean) -> Unit = {},
    onSetHaltSizeImage: (Int) -> Unit = { },
    onSetHalfExpandedSizeImage: (Int) -> Unit = { },
    onClickSelectedProfile: (ProfileStream) -> Unit = {},
    onNavigateListStreams: (String) -> Unit = {}
) {
    val animatedOpacityBackground by animateColorAsState(
        targetValue = if (uiState.showCenterImage) {
            MaterialTheme.colorScheme.background
        } else {
            Color.Transparent
        },
        label = stringResource(Res.string.profile_animation_background_opacity),
        animationSpec = tween(durationMillis = 1000),
        finishedListener = {
            onSetCenterImageAlpha(0f)
            uiState.selectedItem?.let { it1 -> onNavigateListStreams(it1.imageUrl) }
        }
    )

    Scaffold(
        topBar = {
            ProfilePickerStreamToolbar(modifier = Modifier.background(animatedOpacityBackground))
        },
        content = { _ ->
            with(uiState) {
                /**
                 * This code, like others, can't be moved to the ViewModel because it needs to
                 * calculate based on the size of the screen.
                 * Yes, we can pass "LocalDensity.current" to the ViewModel, but this is not a
                 * good practice. The ViewModel is a pure Kotlin class
                 * and this class shouldn't have any dependency on Android (even more,
                 * kmp is coming soon 😎)
                 */
                onSetHaltSizeImage(defaultImageSize.dp.dpToPx() / 2)
                onSetHalfExpandedSizeImage(expandedImageSize.dp.dpToPx() / 2)

                // Preparing animations
                val animatedSizeImage by animateDpAsState(
                    targetValue = if (expandImage) expandedImageSize.dp else defaultImageSize.dp,
                    label = stringResource(Res.string.profile_animation_selected_image_size),
                    animationSpec = tween(durationMillis = 1000),
                )

                val animatedProfileAlpha: Float by animateFloatAsState(
                    if (lastItemPositioned) 1f else 0f,
                    label = stringResource(Res.string.profile_animation_showing_all_profiles),
                )

                val animatedOffsetSelectedProfileImage by animateIntOffsetAsState(
                    targetValue = if (canMoveImageToCenter) {
                        IntOffset(centerScreen.first, centerScreen.second)
                    } else {
                        if (uiState.profilesStream.isNotEmpty() && offsetProfiles.isNotEmpty()) {
                            with(offsetProfiles[uiState.profilesStream.indexOf(selectedItem)]) {
                                IntOffset(first, second)
                            }
                        } else {
                            IntOffset(0, 0)
                        }
                    },
                    label = stringResource(Res.string.profile_animation_selected_image_position),
                    animationSpec = tween(durationMillis = if (!showCenterImage) 100 else 800)
                )

                ProfilePickerProfilesGrid(
                    uiState = uiState,
                    animatedProfileAlpha = animatedProfileAlpha,
                    onSetScreenSize = onSetScreenSize,
                    onClickSelectedProfile = onClickSelectedProfile,
                    onSetLastItemPositioned = onSetLastItemPositioned
                )

                ProfilePickerOpacityLayer(animatedOpacityBackground)

                ProfilePickerSelectedProfileContainer(
                    state = uiState,
                    offsetSelectedProfileImage = animatedOffsetSelectedProfileImage,
                    animatedSizeImage = animatedSizeImage
                )
            }
        }
    )
}
