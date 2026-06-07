package com.codandotv.streamplayerapp.core.background.work.di

import com.codandotv.streamplayerapp.core.background.work.SyncManager
import org.koin.dsl.module

object SyncModule {
    val module = module(createdAtStart = true) {
        single { SyncManager(get()) }
    }
}
