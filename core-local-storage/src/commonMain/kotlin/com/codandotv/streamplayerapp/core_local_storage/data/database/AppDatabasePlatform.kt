package com.codandotv.streamplayerapp.core_local_storage.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.codandotv.streamplayerapp.core_local_storage.data.dao.FavoriteDao
import com.codandotv.streamplayerapp.core_local_storage.domain.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

expect fun databaseInstance(): AppDatabase

internal const val DB_FILE_NAME = "app-database.db"
