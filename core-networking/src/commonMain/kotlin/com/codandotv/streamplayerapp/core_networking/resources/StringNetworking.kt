package com.codandotv.streamplayerapp.core_networking.resources

import org.jetbrains.compose.resources.StringResource
import streamplayerapp_kmp.core_networking.generated.resources.Res
import streamplayerapp_kmp.core_networking.generated.resources.core_networking_msg_default_error
import streamplayerapp_kmp.core_networking.generated.resources.core_networking_networking_error
import streamplayerapp_kmp.core_networking.generated.resources.core_networking_no_data_content
import streamplayerapp_kmp.core_networking.generated.resources.core_networking_no_server_error

// Note: done this to search for strings in the innermost
// layers because you can't use them because they are not
// composable
object StringNetworking {
    internal fun msgDefaultErrorKey(): String = Res.string.core_networking_msg_default_error.key
    internal fun msgNoDataContentKey(): String = Res.string.core_networking_no_data_content.key
    internal fun msgServerErrorKey(): String = Res.string.core_networking_no_server_error.key
    internal fun msgNetworkErrorKey(): String = Res.string.core_networking_networking_error.key

    private val errorMessageMap = mapOf(
        msgDefaultErrorKey() to Res.string.core_networking_msg_default_error,
        msgNoDataContentKey() to Res.string.core_networking_no_data_content,
        msgServerErrorKey() to Res.string.core_networking_no_server_error,
        msgNetworkErrorKey() to Res.string.core_networking_networking_error
    )

    fun getStringResource(key: String): StringResource? = errorMessageMap[key]
}
