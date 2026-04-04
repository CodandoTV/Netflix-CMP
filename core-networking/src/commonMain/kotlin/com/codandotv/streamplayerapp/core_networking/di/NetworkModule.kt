package com.codandotv.streamplayerapp.core_networking.di

import com.codandotv.streamplayerapp.core_networking.HttpClientBuilder
import core.networking.BuildKonfig
import io.ktor.client.HttpClient
import org.koin.core.annotation.Module
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Single

@Module
class NetworkModule {

    @Single
    fun provideHttpClient() : HttpClient {
        return HttpClientBuilder.build(
            baseUrl = BuildKonfig.HOST,
        )
    }

    @Single
    @Qualifier(QualifierProfileHttpClient::class)
    fun provideProfileHttpClient(): HttpClient {
        return HttpClientBuilder.build(
            baseUrl = BuildKonfig.PROFILE,
        )
    }
}

internal object Network {
    const val TIMEOUT = 10000L
}