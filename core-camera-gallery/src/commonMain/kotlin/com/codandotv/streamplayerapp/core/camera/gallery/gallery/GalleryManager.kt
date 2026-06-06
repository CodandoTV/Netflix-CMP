package com.codandotv.streamplayerapp.core.camera.gallery.gallery

import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core.camera.gallery.SharedImage

expect class GalleryManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
@Composable
expect fun rememberGalleryManager(onImageSelected: (SharedImage?) -> Unit): GalleryManager
