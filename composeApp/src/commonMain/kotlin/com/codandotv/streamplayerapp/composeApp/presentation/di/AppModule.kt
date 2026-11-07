package com.codandotv.streamplayerapp.composeApp.presentation.di

import com.codandotv.streamplayerapp.core.background.work.di.SyncModule
import com.codandotv.streamplayerapp.core.local.storage.di.LocalStorageModule
import com.codandotv.streamplayerapp.core.session.di.coreSessionModule
import com.codandotv.streamplayerapp.core.shared.qualifier.QualifierDispatcherIO
import com.codandotv.streamplayerapp.feature.liststreams.list.di.ListStreamModule
import io.kotzilla.generated.monitoring
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.plugin.module.dsl.startKoin

fun streamPlayerApplication(platformBlock: KoinApplication.() -> Unit): KoinApplication {
    return startKoin<KoinApp> {
        platformBlock()

        // Annotated modules (NetworkModule, SearchModule, NewsScreenModule,
        // ProfilePickerStreamModule) are discovered from @Configuration by the
        // Koin Compiler Plugin — only the DSL modules are listed here.
        modules(
            PermissionsModule.module,
            module {
                single(QualifierDispatcherIO) {
                    Dispatchers.IO
                }
            },
            coreSessionModule,
            LocalStorageModule.module,
            SyncModule.module,

            // region feature_modules
            ListStreamModule.module
            // endregion
        )

        monitoring {
            onConfig {
                useIosCrashReport = false
            }
        }
    }
}
