package com.codandotv.streamplayerapp.core_shared_ui.widget


expect class SharedHandlerPlatform{
    fun shareWhatsApp(title: String, url: String)
    fun shareSms(title: String, url: String)
    fun shareInstagram(url: String)
    fun copyToClipboard(url: String)
    fun shareMoreOptions(title: String, url: String)
}

expect fun getSharedHandlerPlatform() : SharedHandlerPlatform
