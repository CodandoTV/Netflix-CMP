package com.codandotv.streamplayerapp.di

import com.codandotv.streamplayerapp.presentation.components.LottieViewProvider
import io.kotzilla.generated.monitoring
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
            monitoring(
                onConfig = {
                    onConfig { useIosCrashReport = false }
                }
            )
        }
    }
}
