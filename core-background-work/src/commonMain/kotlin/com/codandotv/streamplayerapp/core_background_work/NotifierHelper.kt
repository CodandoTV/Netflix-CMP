package com.codandotv.streamplayerapp.core_background_work

import com.mmk.kmpnotifier.notification.NotificationImage
import com.mmk.kmpnotifier.notification.Notifier
import com.mmk.kmpnotifier.notification.NotifierManager
import kotlin.random.Random

object NotifierHelper {
    fun showSimpleNotification(
        title: String = "Notificação Simples",
        body: String = "Corpo da Notificação Simples",
        imageUrl: String = "https://github.com/user-attachments/assets/a0f38159-b31d-4a47-97a7-cc230e15d30b"
    ) {
        val notifier = NotifierManager.getLocalNotifier()
        notifier.notify {
            id = Random.Default.nextInt(0, Int.MAX_VALUE)
            this.title = title
            this.body = body
            payloadData = mapOf(
                Notifier.Companion.KEY_URL to imageUrl,
                "extraKey" to "randomValue"
            )
            image = NotificationImage.Url(imageUrl)
        }
    }
}