package com.codandotv.streamplayerapp.core_shared_ui.widget

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class SharedHandlerPlatform : KoinComponent {
    private val context: Context by inject()

    actual fun shareWhatsApp(title: String, url: String) {
        val message = "$title\n$url"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            `package` = "com.whatsapp"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        runCatching {
            context.startActivity(intent)
        }.onFailure {
            Toast.makeText(context, "WhatsApp não está instalado.", Toast.LENGTH_SHORT).show()
        }
    }

    actual fun shareSms(title: String, url: String) {
        val message = "$title\n$url"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("sms:")
            putExtra("sms_body", message)
        }
        runCatching {
            context.startActivity(intent)
        }.onFailure {
            Toast.makeText(context, "Nenhum aplicativo de SMS encontrado.", Toast.LENGTH_SHORT).show()
        }
    }

    actual fun shareInstagram(url: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            `package` = "com.instagram.android"
            putExtra(Intent.EXTRA_TEXT, url)
        }
        runCatching {
            context.startActivity(intent)
        }.onFailure {
            Toast.makeText(context, "Instagram não está instalado.", Toast.LENGTH_SHORT).show()
        }
    }

    actual fun copyToClipboard(url: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("URL", url)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Link copiado!", Toast.LENGTH_SHORT).show()
    }

    actual fun shareMoreOptions(title: String, url: String) {
        val message = "$title\n$url"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }

        val chooserIntent = Intent.createChooser(intent, "Compartilhar via")
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(chooserIntent)
    }
}

actual fun getSharedHandlerPlatform(): SharedHandlerPlatform = SharedHandlerPlatform()
