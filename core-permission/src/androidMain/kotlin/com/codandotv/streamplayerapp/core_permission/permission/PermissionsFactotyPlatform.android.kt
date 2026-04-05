package com.codandotv.streamplayerapp.core_permission.permission

import android.content.Context
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal actual class PermissionFactory : KoinComponent {
    private val context by inject<Context>()

    actual fun getPermissionFactory(): PermissionsControllerFactory =
        PermissionsControllerFactory {
            PermissionsController(applicationContext = context)
        }
}
