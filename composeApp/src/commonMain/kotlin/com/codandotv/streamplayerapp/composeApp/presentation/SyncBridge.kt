package com.codandotv.streamplayerapp.composeApp.presentation

import com.codandotv.streamplayerapp.core.background.work.SyncManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

object SyncBridge {
    suspend fun syncData() {
        KoinPlatform.getKoin().get<SyncManager>().syncData()
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
