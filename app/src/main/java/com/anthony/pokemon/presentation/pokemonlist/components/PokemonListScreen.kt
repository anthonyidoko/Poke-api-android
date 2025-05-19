package com.anthony.pokemon.presentation.pokemonlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anthony.pokemon.R
import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.presentation.common.IndicatorSize
import com.anthony.pokemon.presentation.common.ProgressIndicator

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    gridState: LazyGridState,
    shouldLoadMore: Boolean,
    pokemons: List<Pokemon>,
    onClick: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        state = gridState,
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Adaptive(150.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonCard(
                pokemon = pokemon,
                onClick = onClick
            )
        }

        if (shouldLoadMore) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row{
                    ProgressIndicator(size = IndicatorSize.SMALL)
                    Text(
                        modifier = Modifier.weight(1f),
                        text= stringResource(R.string.loading_more))
                }
            }
        }

    }
}