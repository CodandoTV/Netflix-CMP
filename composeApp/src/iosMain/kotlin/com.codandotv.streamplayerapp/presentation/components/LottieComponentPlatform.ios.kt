package com.codandotv.streamplayerapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import org.koin.mp.KoinPlatform
import platform.UIKit.UIColor
import platform.UIKit.UIView

@Composable
actual fun LottieComponent(
    jsonString: String,
    modifier: Modifier,
    onAnimationFinished: () -> Unit
) {
    val provider = KoinPlatform.getKoin().get<LottieViewProvider>()

    UIKitView(
        modifier = modifier,
        factory = {
            val view = provider.provideLottieView(jsonString,onAnimationFinished)
            view.backgroundColor = UIColor.blackColor()
            view
        }
    )
}