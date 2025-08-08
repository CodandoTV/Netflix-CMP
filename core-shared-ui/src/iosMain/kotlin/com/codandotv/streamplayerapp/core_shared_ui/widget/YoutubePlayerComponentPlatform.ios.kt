@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.WebKit.WKWebsiteDataStore

@Composable
actual fun YoutubePlayerComponentPlatform(videoId: String, modifier: Modifier){
    val webView = remember {
        val embedHTML = videoId.videoIdToEmbedHTML()
        WKWebView().apply {
            WKWebViewConfiguration().apply {
                websiteDataStore = WKWebsiteDataStore.defaultDataStore()
                limitsNavigationsToAppBoundDomains = false
            }
            configuration.websiteDataStore = WKWebsiteDataStore.defaultDataStore()
            configuration.limitsNavigationsToAppBoundDomains = false
            loadHTMLString(
                string = embedHTML,
                baseURL = null
            )
        }
    }

    UIKitView(
        modifier = modifier,
        factory = {
            webView
        },
        update = { }
    )
}