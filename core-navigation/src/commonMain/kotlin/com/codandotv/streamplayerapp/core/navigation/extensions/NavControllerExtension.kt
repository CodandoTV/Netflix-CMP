package com.codandotv.streamplayerapp.core.navigation.extensions

import androidx.navigation.NavController

fun NavController.goBack() = this.navigateUp()
