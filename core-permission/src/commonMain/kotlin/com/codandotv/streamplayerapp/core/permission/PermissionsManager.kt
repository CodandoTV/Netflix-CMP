package com.codandotv.streamplayerapp.core.permission

import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.gallery.GALLERY
import dev.icerock.moko.permissions.Permission as MokoPermission

enum class AppPermission {
    CAMERA,
    GALLERY
}

enum class PermissionStatus {
    GRANTED,
    DENIED,
    NOT_DETERMINED,
    DENIED_ALWAYS
}

interface PermissionsManager {
    suspend fun request(
        vararg permissions: AppPermission,
        blockDenied: () -> Unit,
        blockDeniedAlways: () -> Unit,
        blockSuccess: (Map<AppPermission, PermissionStatus>) -> Unit
    )

    fun openSettings()
    val controller: PermissionsController
}

class PermissionsManagerImpl(
    override val controller: PermissionsController
) : PermissionsManager {
    private fun AppPermission.toMoko(): MokoPermission = when (this) {
        AppPermission.CAMERA -> MokoPermission.CAMERA
        AppPermission.GALLERY -> MokoPermission.GALLERY
    }

    private fun PermissionState.toPermissionStatus(): PermissionStatus = when (this) {
        PermissionState.Granted -> PermissionStatus.GRANTED
        PermissionState.NotGranted,
        PermissionState.Denied -> PermissionStatus.DENIED

        PermissionState.DeniedAlways -> PermissionStatus.DENIED_ALWAYS
        PermissionState.NotDetermined -> PermissionStatus.NOT_DETERMINED
    }

    override suspend fun request(
        vararg permissions: AppPermission,
        blockDenied: () -> Unit,
        blockDeniedAlways: () -> Unit,
        blockSuccess: (Map<AppPermission, PermissionStatus>) -> Unit
    ) {
        runCatching {
            permissions.forEach {
                controller.providePermission(it.toMoko())
            }

            permissions.associateWith { permission ->
                controller.getPermissionState(permission.toMoko()).toPermissionStatus()
            }
        }.onSuccess { results ->
            blockSuccess.invoke(results)
        }.onFailure { throwable ->
            when (throwable) {
                is DeniedAlwaysException -> blockDeniedAlways.invoke()
                is DeniedException -> blockDenied.invoke()
            }
        }
    }

    override fun openSettings() {
        controller.openAppSettings()
    }
}
