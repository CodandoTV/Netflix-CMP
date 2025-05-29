package com.codandotv.streamplayerapp.core_background_work.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.codandotv.streamplayerapp.core_background_work.SyncManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val syncManager: SyncManager by inject()

    override suspend fun doWork(): Result {
        return try {
            syncManager.syncData()
            Result.success()
        } catch (e: Exception) {
            println("Erro no SyncWorker ${e.message}")
            Result.retry()
        }
    }
}
