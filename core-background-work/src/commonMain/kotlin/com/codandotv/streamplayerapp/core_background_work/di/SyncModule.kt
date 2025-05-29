package com.codandotv.streamplayerapp.core_background_work.di

import com.codandotv.streamplayerapp.core_background_work.SyncManager
import org.koin.dsl.module

object SyncModule {
    val module = module {
        single { SyncManager(get()) }
    }
}