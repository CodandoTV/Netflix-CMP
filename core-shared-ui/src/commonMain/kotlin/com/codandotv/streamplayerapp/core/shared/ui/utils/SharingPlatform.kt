package com.codandotv.streamplayerapp.core.shared.ui.utils

import androidx.compose.runtime.Composable

@Composable
expect fun isPackageInstalled(packageName: String): Boolean
