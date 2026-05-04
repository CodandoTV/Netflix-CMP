package com.codandotv.streamplayerapp.presentation

import android.app.Application
import com.codandotv.streamplayerapp.core.shared.ui.R
import com.codandotv.streamplayerapp.core_background_work.worker.WorkScheduler
import com.codandotv.streamplayerapp.di.streamPlayerApplication
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import io.kotzilla.generated.monitoring
import org.koin.android.ext.koin.androidContext

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        streamPlayerApplication {
            androidContext(this@CustomApplication.applicationContext)
            monitoring()
        }

        WorkScheduler.scheduleSync(this)
        initializeNotification()
    }

    fun initializeNotification() {
        NotifierManager.initialize(
            configuration = NotificationPlatformConfiguration.Android(
                notificationIconResId = R.mipmap.ic_netflix,
                showPushNotification = true,
            )
        )
    }
}
