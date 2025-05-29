package com.codandotv.streamplayerapp

import com.codandotv.streamplayerapp.core_background_work.SyncManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin

object SyncBridge {
    suspend fun syncData() {
        getKoin().get<SyncManager>().syncData()
    }

    fun syncData(completionHandler: () -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                syncData()
            } finally {
                completionHandler()
            }
        }
    }
}
