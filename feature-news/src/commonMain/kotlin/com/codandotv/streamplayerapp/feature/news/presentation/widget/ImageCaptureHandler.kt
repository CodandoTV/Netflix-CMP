package com.codandotv.streamplayerapp.feature.news.presentation.widget

class ImageCaptureHandler(
    val onCameraRequest: () -> Unit,
    val onGalleryRequest: () -> Unit,
    val showSettings: () -> Unit,
    val showPermissionDialog: () -> Boolean,
    val hidePermissionDialog: () -> Unit
)
