package com.anthony.pokemon.domain.util

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext


interface Result<out T, out E : DataError> {
    data class Success<out T, out E : DataError>(val data: T) : Result<T, E>
    data class Error<out T, out E : DataError>(val error: E) : Result<T, E>
}


fun <T, E : DataError> Result<T, E>.onSuccess(onAction: (T) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Success -> {
            onAction(data)
            this
        }

        else -> this
    }
}

fun <T, E : DataError> Result<T, E>.onError(onAction: (E) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> {
            onAction(error)
            this
        }

        else -> this
    }
}


fun <T, R> safeCall(
    block: suspend () -> T,
    transform: (T) -> R
): Flow<Result<R, DataError.Network>> = flow {
    println("GeGeGeGe")
    runCatching { withContext(Dispatchers.IO){
        println("Called")
        block()
    } }
        .onSuccess { result ->
            emit(Result.Success(transform(result)))
        }
        .onFailure { exception ->
            if (exception is CancellationException) throw exception
            emit(
                Result.Error(
                    when (exception) {
                        is JsonConvertException -> DataError.Network.SerializationException
                        is ClientRequestException -> DataError.Network.InvalidRequestObject
                        is HttpRequestTimeoutException -> DataError.Network.TimeoutException
                        else -> DataError.Network.UnknownException
                    }
                )
            )
        }
}


