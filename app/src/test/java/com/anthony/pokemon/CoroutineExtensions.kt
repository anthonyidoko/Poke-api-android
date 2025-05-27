package com.anthony.pokemon

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> CoroutineScope.collectFlow(
    stateFlow: StateFlow<T>,
    dispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
): List<T> {
    val actualDeliveredStates = mutableListOf<T>()
    val job = launch(dispatcher) {
        stateFlow.collect { item ->
            println("Current: $item")
            actualDeliveredStates.add(item)
        }
    }
    job.cancel()
    return actualDeliveredStates
}