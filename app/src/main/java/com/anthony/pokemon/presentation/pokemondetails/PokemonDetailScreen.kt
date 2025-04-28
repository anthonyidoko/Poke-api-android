package com.anthony.pokemon.presentation.pokemondetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.presentation.pokemondetails.components.PokemonAbilitiesScreen
import com.anthony.pokemon.presentation.pokemondetails.components.PokemonSpritesScreen
import com.anthony.pokemon.presentation.ui.theme.PokemonTheme

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    state: PokemonDetailUiState,
    onEvent: (PokemonDetailEvent) -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            )
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp)
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokemonSpritesScreen(
                modifier = Modifier.padding(5.dp),
                sprites = state.sprites
            )

            Spacer(Modifier.height(5.dp))

            PokemonAbilitiesScreen(
                abilities = state.abilities,
                fetchingAbilities = state.fetchingAbilities
            )
            Spacer(Modifier.height(5.dp))

        }

    }
}



private val previewMap = buildMap {
    put("Balck", "")
    put("Green", "")
    put("Front", "")
    put("Shiny", "")
    put("Gadus", "")
    put("Non Gad", "")
}

@Preview
@Composable
private fun PokemonDetailScreenPreview() {
    PokemonTheme {
        Surface(Modifier.fillMaxSize()) {
            PokemonDetailScreen(
                state = PokemonDetailUiState(
                    sprites = previewMap,
                    pokemonDetailData = PokemonDetailData(
                ),
                fetchingAbilities = false
            ), onEvent = {})
        }
    }

}