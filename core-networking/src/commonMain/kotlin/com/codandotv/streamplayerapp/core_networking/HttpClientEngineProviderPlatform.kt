package com.codandotv.streamplayerapp.core_networking

import io.ktor.client.engine.HttpClientEngine

expect fun httpClientEnginePlatform(): HttpClientEngine
