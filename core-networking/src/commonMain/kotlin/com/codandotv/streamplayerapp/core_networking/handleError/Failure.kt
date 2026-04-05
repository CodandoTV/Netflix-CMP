package com.codandotv.streamplayerapp.core_networking.handleError

import com.codandotv.streamplayerapp.core_networking.resources.StringNetworking
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

/**
 * Base Class for handling errors/failures/exceptions.
 */
@Suppress(
    "ThrowingExceptionsWithoutMessageOrCause",
    "TooGenericExceptionCaught",
    "MagicNumber"
)

@Serializable
sealed class Failure(
    val code: Int? = -1,
    val errorMessage: String? = null,
    val errorMessageResKey: String = StringNetworking.msgDefaultErrorKey()
) : Exception(), KoinComponent {
    data class NoDataContent(val codeStatus: Int? = null) :
        Failure(codeStatus, errorMessageResKey = StringNetworking.msgNoDataContentKey())

    data class ServerError(val codeStatus: Int? = null) :
        Failure(codeStatus, errorMessageResKey = StringNetworking.msgServerErrorKey())

    data class GenericError(
        val codeStatus: Int? = -12, private val msg: String? = StringNetworking.msgNetworkErrorKey()
    ) : Failure(
        codeStatus
    )

    data class NetworkError(
        val codeStatus: Int? = -13, private val throwable: Throwable
    ) : Failure(
        codeStatus, errorMessageResKey = StringNetworking.msgNetworkErrorKey()
    )

    data class UnknownError(
        val codeStatus: Int? = null, private val throwable: Throwable? = Exception()
    ) : Failure(
        codeStatus, throwable?.message
    )

    data class UnexpectedApiException(
        val codeStatus: Int? = -14, private val throwable: Throwable? = Exception()
    ) : Failure(
        codeStatus, throwable?.message
    )

    data class ClientException(
        val codeStatus: Int? = -15, private val throwable: Throwable? = Exception()
    ) : Failure(
        codeStatus, throwable?.message
    )

    data class UnparsableResponseException(
        val codeStatus: Int? = -16, private val throwable: Throwable? = Exception()
    ) : Failure(
        codeStatus, throwable?.message
    )
}
