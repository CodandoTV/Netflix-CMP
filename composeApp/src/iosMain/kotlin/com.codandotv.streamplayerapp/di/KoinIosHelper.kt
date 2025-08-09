package com.codandotv.streamplayerapp.di

import com.codandotv.streamplayerapp.presentation.components.LottieViewProvider
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinIosHelper {
    fun initKoin(lottieViewProvider: LottieViewProvider) {
        startKoin {
            modules(AppModule.list + module {
                single<LottieViewProvider> {
                    lottieViewProvider
                }
            })
        }
    }
}