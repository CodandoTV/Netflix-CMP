package com.codandotv.streamplayerapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinIosHelper {
    fun initKoin() {
        startKoin {
            modules(AppModule.list)
        }
    }
}