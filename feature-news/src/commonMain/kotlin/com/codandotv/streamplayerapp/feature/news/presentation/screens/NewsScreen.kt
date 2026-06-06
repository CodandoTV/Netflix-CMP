package com.codandotv.streamplayerapp.feature.news.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.core.camera.gallery.SharedImage
import com.codandotv.streamplayerapp.core.camera.gallery.camera.rememberCameraManager
import com.codandotv.streamplayerapp.core.camera.gallery.gallery.rememberGalleryManager
import com.codandotv.streamplayerapp.core.navigation.bottomnavigation.StreamPlayerBottomNavigation
import com.codandotv.streamplayerapp.core.permission.PermissionDeniedDialog
import com.codandotv.streamplayerapp.feature.news.presentation.NewsScreenActionTakeImage
import com.codandotv.streamplayerapp.feature.news.presentation.NewsScreenViewModel
import com.codandotv.streamplayerapp.feature.news.presentation.widget.ImagePickerContent
import dev.icerock.moko.permissions.compose.BindEffect
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import streamplayerapp_kmp.feature_news.generated.resources.Res
import streamplayerapp_kmp.feature_news.generated.resources.error_image_loaded
import streamplayerapp_kmp.feature_news.generated.resources.select_image_subtitle
import streamplayerapp_kmp.feature_news.generated.resources.select_image_title

@Composable
fun NewsScreenContent(
    navController: NavController,
    viewModel: NewsScreenViewModel = koinViewModel()
) {
    val errorMessage = stringResource(Res.string.error_image_loaded)
    val sharedImageState = remember { mutableStateOf<SharedImage?>(null) }
    val hasTriedCapture = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsState()
    val cameraManager = rememberCameraManager { sharedImage ->
        sharedImageState.value = sharedImage
        hasTriedCapture.value = true
    }

    val galleryManager = rememberGalleryManager { sharedImage ->
        sharedImageState.value = sharedImage
        hasTriedCapture.value = true
    }

    BindEffect(viewModel.permissionsManager.controller)

    LaunchedEffect(Unit) {
        viewModel.requestPermission()
    }

    LaunchedEffect(Unit) {
        viewModel.actionTakeImage.collect { action ->
            when (action) {
                NewsScreenActionTakeImage.Camera -> cameraManager.launch()
                NewsScreenActionTakeImage.Gallery -> galleryManager.launch()
            }
        }
    }

    LaunchedEffect(hasTriedCapture.value, sharedImageState.value) {
        if (hasTriedCapture.value && sharedImageState.value == null) {
            snackbarHostState.showSnackbar(errorMessage)
            hasTriedCapture.value = false
        }
    }

    if (uiState.showPermissionDialog) {
        PermissionDeniedDialog(
            onDismiss = { viewModel.dismissPermissionDialog() },
            onSettingsClick = {
                viewModel.goToSetting()
            }
        )
    }

    NewsScreen(
        navController = navController,
        onClickCamera = {
            viewModel.openCamera()
        },
        onClickGallery = {
            viewModel.openGallery()
        },
        sharedImage = sharedImageState.value,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun NewsScreen(
    navController: NavController,
    onClickCamera: () -> Unit,
    onClickGallery: () -> Unit,
    sharedImage: SharedImage? = null,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        bottomBar = {
            StreamPlayerBottomNavigation(navController = navController)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(Res.string.select_image_title),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Text(
                        text = stringResource(Res.string.select_image_subtitle),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    ImagePickerContent(
                        sharedImage = sharedImage,
                        onClickCamera = onClickCamera,
                        onClickGallery = onClickGallery
                    )
                }
            }
        }
    )
}
