package com.codandotv.streamplayerapp.core_networking

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun httpClientEnginePlatform(): HttpClientEngine {
    return Darwin.create()
}
