package com.codandotv.streamplayerapp.core.session.di

import com.codandotv.streamplayerapp.core.session.domain.SessionManager
import org.koin.dsl.module

val coreSessionModule = module {
    factory { SessionManager() }
}
