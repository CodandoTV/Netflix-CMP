package com.codandotv.streamplayerapp.core.local.storage.di

import com.codandotv.streamplayerapp.core.local.storage.data.database.AppDatabase
import com.codandotv.streamplayerapp.core.local.storage.data.database.databaseInstance
import org.koin.dsl.module

object LocalStorageModule {
    val module = module {
        single { databaseInstance() }
        single { get<AppDatabase>().favoriteDao() }
    }
}
