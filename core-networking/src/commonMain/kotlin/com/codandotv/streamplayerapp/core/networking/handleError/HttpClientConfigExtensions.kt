package com.codandotv.streamplayerapp.core.networking.handleError

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request

suspend inline fun <reified T> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): NetworkResponse<T> =
    try {
        val response = request { block() }
        NetworkResponse.Success(response.body())
    } catch (e: Failure) {
        NetworkResponse.Error(exception = e)
    }
