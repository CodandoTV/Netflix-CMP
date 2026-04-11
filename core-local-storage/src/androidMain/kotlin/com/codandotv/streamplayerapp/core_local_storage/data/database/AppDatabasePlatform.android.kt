package com.codandotv.streamplayerapp.core_local_storage.data.database

import androidx.room.Room
import org.koin.mp.KoinPlatform

actual fun databaseInstance(): AppDatabase {
    return Room.databaseBuilder(
        KoinPlatform.getKoin().get(),
        AppDatabase::class.java,
        DB_FILE_NAME
    ).build()
}
