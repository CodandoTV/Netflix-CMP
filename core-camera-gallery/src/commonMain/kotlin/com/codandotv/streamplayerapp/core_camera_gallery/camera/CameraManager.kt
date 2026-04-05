package com.codandotv.streamplayerapp.core_camera_gallery.camera

import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core_camera_gallery.SharedImage

@Composable
expect fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager


expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
