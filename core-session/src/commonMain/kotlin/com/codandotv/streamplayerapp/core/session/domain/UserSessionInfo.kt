package com.codandotv.streamplayerapp.core.session.domain

import kotlin.time.Instant

data class UserSessionInfo(
    val userTimestamp: Instant,
    val profileImageUrl: String,
)