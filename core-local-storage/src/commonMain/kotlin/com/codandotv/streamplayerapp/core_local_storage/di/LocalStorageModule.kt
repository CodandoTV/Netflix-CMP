package com.codandotv.streamplayerapp.core_local_storage.di

import com.codandotv.streamplayerapp.core_local_storage.data.database.AppDatabase
import com.codandotv.streamplayerapp.core_local_storage.data.database.databaseInstance
import org.koin.dsl.module

object LocalStorageModule {
    val module = module {
        single { databaseInstance() }
        single { get<AppDatabase>().favoriteDao() }
    }
}
