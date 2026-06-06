package com.codandotv.streamplayerapp.di

import com.codandotv.streamplayerapp.core.background.work.di.SyncModule
import com.codandotv.streamplayerapp.core.local.storage.di.LocalStorageModule
import com.codandotv.streamplayerapp.core.networking.di.NetworkModule
import com.codandotv.streamplayerapp.core.shared.qualifier.QualifierDispatcherIO
import com.codandotv.streamplayerapp.feature.news.di.NewsScreenModule
import io.kotzilla.generated.monitoring
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.lazyModules
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun streamPlayerApplication(platformBlock: KoinApplication.() -> Unit): KoinApplication {
    return startKoin {
        platformBlock()
        lazyModules(PermissionsModule.module)

        modules(
            module {
                single(QualifierDispatcherIO) {
                    Dispatchers.IO
                }
            },
            NetworkModule().module,
            LocalStorageModule.module,
            SyncModule.module,

            // region feature_modules
            com.codandotv.streamplayerapp.feature.liststreams.list.di.ListStreamModule.module,
            com.codandotv.streamplayerapp.feature.search.di.SearchModule().module,
            NewsScreenModule().module,
            com.codandotv.streamplayerapp.feature.profile.di.ProfilePickerStreamModule().module
            // endregion
        )

        monitoring {
            onConfig {
                useIosCrashReport = false
            }
        }
    }
}

