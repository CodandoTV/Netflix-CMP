package com.codandotv.streamplayerapp.core.networking.di

import com.codandotv.streamplayerapp.core.networking.HttpClientBuilder
import core.networking.BuildKonfig
import io.ktor.client.HttpClient
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Singleton

@Module
@Configuration
@ComponentScan("com.codandotv.streamplayerapp.core.networking.di")
class NetworkModule {

    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientBuilder.build(
            baseUrl = BuildKonfig.HOST,
        )
    }

    @Singleton
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
