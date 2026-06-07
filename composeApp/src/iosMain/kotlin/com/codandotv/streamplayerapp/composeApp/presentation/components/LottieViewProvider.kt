package com.codandotv.streamplayerapp.composeApp.presentation.components

import platform.UIKit.UIView

interface LottieViewProvider {
    fun provideLottieView(lottieAnimationJson: String, onAnimationFinish: () -> Unit): UIView
}
