package com.anthony.pokemon.presentation.pokemonlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anthony.pokemon.R
import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.model.getImageUrl
import com.anthony.pokemon.presentation.ui.theme.PokemonTheme

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onClick: (String) -> Unit
) {
    Surface(
        modifier = modifier.wrapContentSize()
            .testTag(
                stringResource(R.string.pokemon_card_test_tag)),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.clickable {
                onClick(pokemon.name.orEmpty())
            }.testTag(stringResource(R.string.pokemon_card_test_tag_click))
        ) {

            Card(
                shape = RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 10.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                PokemonImage(
                    modifier = Modifier.fillMaxSize(),
                    url = pokemon.getImageUrl(),
                    pokemonName = pokemon.name.orEmpty(),

                    )
            }

            Spacer(Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = pokemon.name.orEmpty().uppercase(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = FontFamily.Serif,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
private fun PokemonCardPreview() {
    PokemonTheme {
        PokemonCard(
            pokemon =
            Pokemon(
                name = "bulbasaur",
                detailUrl = "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            onClick = {}
        )
    }
}