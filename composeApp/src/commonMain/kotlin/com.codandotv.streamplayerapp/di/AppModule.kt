package com.codandotv.streamplayerapp.di

import com.codandotv.streamplayerapp.core_background_work.di.SyncModule
import com.codandotv.streamplayerapp.core_local_storage.di.LocalStorageModule
import com.codandotv.streamplayerapp.core_networking.di.NetworkModule
import com.codandotv.streamplayerapp.core_shared.qualifier.QualifierDispatcherIO
import com.codandotv.streamplayerapp.feature_list_streams.list.di.ListStreamModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun streamPlayerApplication(platformBlock: KoinApplication.() -> Unit): KoinApplication {
    return startKoin {
        platformBlock()
        modules(
            module { single(QualifierDispatcherIO) { Dispatchers.IO } },
            NetworkModule().module,
            LocalStorageModule.module,
            SyncModule.module,
            ListStreamModule.module,
        )
    }
}

