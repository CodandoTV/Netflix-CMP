package com.codandotv.streamplayerapp.core_shared_ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.codandotv.streamplayerapp.core_shared_ui.extension.getPackageInfoCompat

@Composable
actual fun isPackageInstalled(packageName: String): Boolean {
    val pm = LocalContext.current.packageManager
    return runCatching {
        pm.getPackageInfoCompat(packageName)
        true
    }.getOrDefault(false)
}
