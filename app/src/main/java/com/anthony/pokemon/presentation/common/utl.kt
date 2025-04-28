package com.anthony.pokemon.presentation.common

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

fun LazyGridState.isCloseToEnd(offset: Int = 3): Boolean{
    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return false
    return lastVisibleItemIndex >= layoutInfo.totalItemsCount-offset
}

@Composable
inline fun <reified T: ViewModel>NavBackStackEntry.sharedKoinViewModel(controller: NavHostController): T{
    val parentRoute = this.destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        controller.getBackStackEntry(parentRoute)
    }
    return koinViewModel<T>(viewModelStoreOwner = parentEntry)
}