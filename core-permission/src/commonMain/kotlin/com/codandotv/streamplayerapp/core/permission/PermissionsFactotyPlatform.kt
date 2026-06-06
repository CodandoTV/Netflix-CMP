package com.codandotv.streamplayerapp.core.permission

import dev.icerock.moko.permissions.compose.PermissionsControllerFactory


internal expect class PermissionFactory() {
    fun getPermissionFactory(): PermissionsControllerFactory
}
