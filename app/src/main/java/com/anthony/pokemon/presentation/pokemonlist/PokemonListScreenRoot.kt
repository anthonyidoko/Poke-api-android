package com.anthony.pokemon.presentation.pokemonlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anthony.pokemon.R
import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.presentation.common.IndicatorSize
import com.anthony.pokemon.presentation.common.ProgressIndicator
import com.anthony.pokemon.presentation.common.isCloseToEnd
import com.anthony.pokemon.presentation.pokemonlist.components.PokemonListScreen
import com.anthony.pokemon.presentation.ui.theme.PokemonTheme

@Composable
fun PokemonListScreenRoot(
    showSnackBar: (String) -> Unit,
    state: PokemonListUiState,
    onEvent: (PokemonListUiEvent) -> Unit,
) {
    val gridState = rememberLazyGridState()
    val closeToBottom by remember {
        derivedStateOf { gridState.isCloseToEnd() }
    }
    LaunchedEffect(closeToBottom, state.shouldLoadMore) {
        if (closeToBottom) {
            onEvent(PokemonListUiEvent.LoadMore)
        }
    }
    if (state.errorMessage != null) {
        showSnackBar(state.errorMessage.getString())
    }
    if (state.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ProgressIndicator(
                    modifier = Modifier.testTag(stringResource(R.string.loading_dialog_tag)),
                    size = IndicatorSize.LARGE
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    text = state.loadingMessage?.getString().orEmpty()
                )
            }
        }
    }

    if (state.pokemons.isNotEmpty() || state.shouldLoadMore) {
        PokemonListScreen(
            shouldLoadMore = state.shouldLoadMore,
            gridState = gridState,
            pokemons = state.pokemons,
            onClick = { onEvent(PokemonListUiEvent.OnPokemonClick(it)) }
        )
    }
}

@Preview(showBackground = true, name = "Pokemon List Screen")
@Composable
private fun PokemonListPrev() {
    val pokemons = (1..50).map {
        Pokemon(
            name = "Pokemon $it",
            detailUrl = ""
        )
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        PokemonTheme {
            PokemonListScreenRoot(
                showSnackBar = { _ -> },
                state = PokemonListUiState(
                    pokemons = pokemons,
                ),
                onEvent = { _ -> }
            )
        }
    }
}
