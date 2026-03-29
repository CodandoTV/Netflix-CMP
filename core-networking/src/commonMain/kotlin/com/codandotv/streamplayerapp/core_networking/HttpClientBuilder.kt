package com.codandotv.streamplayerapp.core_networking

import com.codandotv.streamplayerapp.core_networking.di.Network.TIMEOUT
import com.codandotv.streamplayerapp.core_networking.handleError.Failure
import core.networking.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

internal object HttpClientBuilder {
    fun build(baseUrl: String) : HttpClient {
        return HttpClient(engine = httpClientEnginePlatform()) {
            expectSuccess = false

            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                socketTimeoutMillis = TIMEOUT
                requestTimeoutMillis = TIMEOUT
                connectTimeoutMillis = TIMEOUT
            }

            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = BuildKonfig.API_BEARER_AUTH,
                            refreshToken = ""
                        )
                    }
                }
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        //TODO: Migrar Logs para Utilizar Kermit
                        println("HttpClient${message}")
                    }
                }
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    throw when (cause) {
                        is ClientRequestException -> {
                            Failure.ServerError(codeStatus = cause.response.status.value)
                        }

                        is SerializationException -> {
                            Failure.UnparsableResponseException(throwable = cause)
                        }

                        is IOException -> {
                            Failure.NetworkError(throwable = cause)
                        }

                        else -> {
                            Failure.UnknownError(throwable = cause)
                        }
                    }
                }
            }
        }
    }
}