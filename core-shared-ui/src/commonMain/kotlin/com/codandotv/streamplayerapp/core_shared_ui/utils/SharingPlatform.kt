package com.codandotv.streamplayerapp.core_shared_ui.utils

import androidx.compose.runtime.Composable


@Composable
expect fun isPackageInstalled(packageName: String) : Boolean
