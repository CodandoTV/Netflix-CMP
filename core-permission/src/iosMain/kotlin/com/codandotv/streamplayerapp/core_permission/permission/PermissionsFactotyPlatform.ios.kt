package com.codandotv.streamplayerapp.core_permission.permission

import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.ios.PermissionsController

internal actual class PermissionFactory {
    actual fun getPermissionFactory(): PermissionsControllerFactory =
        PermissionsControllerFactory {
            PermissionsController()
    }
}
