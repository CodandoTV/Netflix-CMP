package com.codandotv.streamplayerapp.di

import com.codandotv.streamplayerapp.core_background_work.di.SyncModule
import com.codandotv.streamplayerapp.core_local_storage.di.LocalStorageModule
import com.codandotv.streamplayerapp.core_networking.di.NetworkModule
import com.codandotv.streamplayerapp.core_shared.qualifier.QualifierDispatcherIO
import com.codandotv.streamplayerapp.feature_list_streams.list.di.ListStreamModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

object AppModule {
    private val module = module {
        single(QualifierDispatcherIO) { Dispatchers.IO }
    }
    val list  = module + NetworkModule.module + LocalStorageModule.module + SyncModule.module + ListStreamModule.module
}