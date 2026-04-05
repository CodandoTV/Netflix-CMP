package com.codandotv.streamplayerapp.core_permission.permission

import dev.icerock.moko.permissions.compose.PermissionsControllerFactory


internal expect class PermissionFactory() {
    fun getPermissionFactory() : PermissionsControllerFactory
}
