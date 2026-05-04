package com.codandotv.streamplayerapp.di

import com.codandotv.streamplayerapp.presentation.components.LottieViewProvider
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
