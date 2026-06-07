@file:Suppress("MagicNumber")

package com.codandotv.streamplayerapp.core.background.work

import com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

class SyncManager(
    private val repository: ListStreamRepository
) {
    suspend fun syncData() {
        val title: Stream = repository.topRatedStream().first()
        val messageTitle = "${title.name} -Já disponível no app!"
        val messageBody = "Confira a sinopse: ${title.description}"
        val imageUrl = title.posterPathUrl

        NotifierHelper.showSimpleNotification(
            title = messageTitle,
            body = messageBody,
            imageUrl = imageUrl
        )

        println("SyncManager: Fazendo alguma tarefa de sincronização...")
        delay(2000)
        println("SyncManager: Sincronização concluída!")
    }
}
