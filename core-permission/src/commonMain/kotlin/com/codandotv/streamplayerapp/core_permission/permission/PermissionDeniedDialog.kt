package com.codandotv.streamplayerapp.core_permission.permission

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.core_permission.generated.resources.Res
import streamplayerapp_kmp.core_permission.generated.resources.permission_dialog_confirm
import streamplayerapp_kmp.core_permission.generated.resources.permission_dialog_dismiss
import streamplayerapp_kmp.core_permission.generated.resources.permission_dialog_text
import streamplayerapp_kmp.core_permission.generated.resources.permission_dialog_title

@Composable
fun PermissionDeniedDialog(
    onDismiss: () -> Unit,
    onSettingsClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onSettingsClick,
                colors = textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(Res.string.permission_dialog_confirm),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(
                    text = stringResource(Res.string.permission_dialog_dismiss),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        title = {
            Text(
                text = stringResource(Res.string.permission_dialog_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Text(
                text = stringResource(Res.string.permission_dialog_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
