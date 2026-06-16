package com.codandotv.streamplayerapp.core.session.domain

import com.codandotv.streamplayerapp.core.session.di.SessionScope
import org.koin.mp.KoinPlatform
import kotlin.time.Clock

class SessionManager {
    private val scopeId: String = "user_session"

    fun openSession(imageUrl: String) {
        closeSession()
        val koin = KoinPlatform.getKoin()

        val scope = koin.createScope<SessionScope>(
            scopeId = scopeId,
        )
        scope.declare(
            UserSessionInfo(
                userTimestamp = Clock.System.now(),
                profileImageUrl = imageUrl
            )
        )
    }

    fun userSessionInfo(): UserSessionInfo? {
        return KoinPlatform.getKoin()
            .getScopeOrNull(scopeId)
            ?.getOrNull<UserSessionInfo>()
    }

    fun closeSession() {
        val koin = KoinPlatform.getKoin()
        koin.getScopeOrNull(scopeId)?.close()
    }
}