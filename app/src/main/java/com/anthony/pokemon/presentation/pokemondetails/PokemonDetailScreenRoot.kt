package com.anthony.pokemon.presentation.pokemondetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel


@Composable
fun PokemonDetailScreenRoot(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<PokemonDetailViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    PokemonDetailScreen(
        modifier = modifier,
        state = state,
        onEvent = viewModel::onEvent
    )
}


