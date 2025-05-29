package com.codandotv.streamplayerapp.presentation

import android.app.Application
import com.codandotv.streamplayerapp.core.shared.ui.R
import com.codandotv.streamplayerapp.core_background_work.worker.WorkScheduler
import com.codandotv.streamplayerapp.di.AppModule
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin{
            androidContext(this@CustomApplication.applicationContext)
            modules(AppModule.list)
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