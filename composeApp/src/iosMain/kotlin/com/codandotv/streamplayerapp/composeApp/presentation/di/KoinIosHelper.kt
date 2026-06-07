package com.codandotv.streamplayerapp.composeApp.presentation.di

import com.codandotv.streamplayerapp.composeApp.presentation.components.LottieViewProvider
import org.koin.dsl.module

class KoinIosHelper {
    fun initKoin(lottieViewProvider: LottieViewProvider) {
        streamPlayerApplication {
            modules(
                module {
                    single<LottieViewProvider> {
                        lottieViewProvider
                    }
                }
            )
        }
    }
}
