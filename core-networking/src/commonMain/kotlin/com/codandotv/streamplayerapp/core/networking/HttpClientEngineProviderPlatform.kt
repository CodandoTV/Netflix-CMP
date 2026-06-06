package com.codandotv.streamplayerapp.core.networking

import io.ktor.client.engine.HttpClientEngine

expect fun httpClientEnginePlatform(): HttpClientEngine
