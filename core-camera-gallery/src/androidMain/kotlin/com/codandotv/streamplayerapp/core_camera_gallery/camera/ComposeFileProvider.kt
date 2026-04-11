package com.codandotv.streamplayerapp.core_camera_gallery.camera

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.codandotv.streamplayerapp.core.camera.gallery.R
import java.io.File
import java.util.Objects

class ComposeFileProvider : FileProvider(
    R.xml.path_provider
) {
    companion object {
        fun getImageUri(context: Context): Uri? = kotlin.runCatching {
            val tempFile = File.createTempFile(
                "picture_${System.currentTimeMillis()}", ".png", context.cacheDir
            ).apply {
                createNewFile()
            }
            val authority = context.applicationContext.packageName + ".provider"

            println("getImageUri: ${tempFile.absolutePath}")

            getUriForFile(
                Objects.requireNonNull(context),
                authority,
                tempFile,
            )
        }.getOrNull()
    }
}
