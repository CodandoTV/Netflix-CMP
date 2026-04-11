package com.codandotv.streamplayerapp.core_shared_ui.widget
import platform.Foundation.NSURL
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UIPasteboard

actual class SharedHandlerPlatform {

    actual fun shareWhatsApp(title: String, url: String) {
        openUrl("whatsapp://send?text=${encode(title)} - ${encode(url)}", "WhatsApp não está instalado.")
    }

    actual fun shareSms(title: String, url: String) {
        openUrl("sms:?&body=${encode(title)} - ${encode(url)}", "Nenhum aplicativo de mensagens disponível.")
    }

    actual fun shareInstagram(url: String) {
        openUrl("instagram://app", "Instagram não está instalado.")
    }

    actual fun copyToClipboard(url: String) {
        UIPasteboard.generalPasteboard.string = url
        showAlert("Link copiado!", "O link foi copiado para a área de transferência.")
    }

    actual fun shareMoreOptions(title: String, url: String) {
        openUrl("mailto:?subject=${encode(title)}&body=${encode(url)}", "Nenhum aplicativo de e-mail disponível.")
    }

    private fun openUrl(url: String, errorMessage: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null && UIApplication.sharedApplication.canOpenURL(nsUrl)) {
            UIApplication.sharedApplication.openURL(nsUrl)
        } else {
            showAlert("Erro", errorMessage)
        }
    }

    private fun encode(text: String): String {
        return text.replace(" ", "%20")
    }

    private fun showAlert(title: String, message: String) {
        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
        rootViewController?.let {
            val alert = UIAlertController.alertControllerWithTitle(title, message, UIAlertControllerStyleAlert)
            alert.addAction(UIAlertAction.actionWithTitle("OK", UIAlertActionStyleDefault, null))
            it.presentViewController(alert, animated = true, completion = null)
        }
    }
}

actual fun getSharedHandlerPlatform(): SharedHandlerPlatform = SharedHandlerPlatform()
