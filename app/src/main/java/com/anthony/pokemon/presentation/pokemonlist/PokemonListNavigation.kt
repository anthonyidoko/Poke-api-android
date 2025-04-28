package com.anthony.pokemon.presentation.pokemonlist

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object PokemonList

fun NavGraphBuilder.pokemonListScreen(
    showSnackBar: (String) -> Unit,
    navigateToDetail: (String) -> Unit
) {
    composable<PokemonList> {
        val viewModel: PokemonListViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PokemonListScreenRoot(
            showSnackBar = showSnackBar,
            state = state,
            onEvent = { event ->
                if (event is PokemonListUiEvent.OnPokemonClick) {
                    val capitalizedName = event.name.replaceFirstChar { it.uppercase() }
                    navigateToDetail(capitalizedName)
                }
                viewModel.onEvent(event)
            }
        )
    }
}

fun NavHostController.navigateToPokemonListScreen() {
    navigate(PokemonList)
}


