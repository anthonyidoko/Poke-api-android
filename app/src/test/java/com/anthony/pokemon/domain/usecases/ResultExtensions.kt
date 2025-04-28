package com.anthony.pokemon.domain.usecases

import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import com.anthony.pokemon.domain.util.onError
import com.anthony.pokemon.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

internal suspend fun <T> Flow<Result<T, DataError.Network>>.getResponseForSuccess(): List<T> {
    return buildList {
        this@getResponseForSuccess.first()
            .onSuccess { add(it) }
    }
}
internal suspend fun <T> Flow<Result<T, DataError.Network>>.getResponseForError(): List<DataError.Network> {
    return buildList {
        this@getResponseForError.first()
            .onError { add(it) }
    }
}