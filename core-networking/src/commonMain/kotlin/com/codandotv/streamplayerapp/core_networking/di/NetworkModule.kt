package com.codandotv.streamplayerapp.core_networking.di

import com.codandotv.streamplayerapp.core_networking.HttpClientBuilder
import core.networking.BuildKonfig
import org.koin.dsl.module

object NetworkModule {
    val module = module {
        single {
            HttpClientBuilder.build(
                baseUrl = BuildKonfig.HOST,
            )
        }

        single(QualifierProfileHttpClient) {
            HttpClientBuilder.build(
                baseUrl = BuildKonfig.PROFILE,
            )
        }
    }
}

internal object Network {
    const val TIMEOUT = 10000L
}