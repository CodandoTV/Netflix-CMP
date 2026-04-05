package com.codandotv.streamplayerapp.core_local_storage.data.database

import androidx.room.Room
import androidx.room.util.findDatabaseConstructorAndInitDatabaseImpl
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    return requireNotNull(documentDirectory).path!!
}

actual fun databaseInstance(): AppDatabase {
    val dbFile = "${documentDirectory()}/$DB_FILE_NAME"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile,
        factory = { findDatabaseConstructorAndInitDatabaseImpl(AppDatabase::class) }
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

