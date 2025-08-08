package com.codandotv.streamplayerapp.core_shared_ui.widget

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.codandotv.streamplayerapp.core_shared_ui.resources.COLOR_BACKGROUND

@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun YoutubePlayerComponentPlatform(videoId: String, modifier: Modifier) {
    val context = LocalContext.current

    val webView = remember {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            setBackgroundColor(COLOR_BACKGROUND.toArgb())
        }
    }

    val embedHTML = remember {
        """
        <html>
        <head><meta name="viewport" content="width=device-width, initial-scale=1.0"></head>
        <body>${videoId.videoIdToEmbedHTML()}</body>
        </html>
        """.trimIndent()
    }

    AndroidView(
        modifier = modifier,
        factory = { webView },
        update = { it.loadDataWithBaseURL(null, embedHTML, "text/html", "UTF-8", null) },
        onReset = { it.destroy() }
    )
}
