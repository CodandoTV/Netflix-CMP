package com.codandotv.streamplayerapp.core_networking.handleError

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class NetworkResponse<out T> {
    @Serializable
    data class Success<out T>(
        val value: T
    ) : NetworkResponse<T>()

    @Serializable
    data class Error(
        val body: String? = null,
        @Transient
        val exception: Failure? = null
    ) : NetworkResponse<Nothing>()
}

fun <T> NetworkResponse<T>.toResult(): Result<T> =
    when (this) {
        is NetworkResponse.Success -> {
            Result.success(this.value)
        }
        is NetworkResponse.Error -> {
            Result.failure(this.exception ?: Failure.GenericError())
        }
    }

fun <T> NetworkResponse<T>.toFlow(): Flow<T> {
    val networkResponse = this
    return flow {
        when (networkResponse) {
            is NetworkResponse.Success -> {
                emit(networkResponse.value)
            }
            is NetworkResponse.Error -> {
                throw networkResponse.exception ?: Failure.GenericError()
            }
        }
    }
}
