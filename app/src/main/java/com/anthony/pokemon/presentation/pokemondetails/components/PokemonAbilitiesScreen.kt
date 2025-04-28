package com.anthony.pokemon.presentation.pokemondetails.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anthony.pokemon.R
import com.anthony.pokemon.domain.model.PokemonAbility

@Composable
fun PokemonAbilitiesScreen(
    modifier: Modifier = Modifier,
    abilities: List<PokemonAbility>,
    fetchingAbilities: Boolean
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(!fetchingAbilities){
            Text(
                text = stringResource(R.string.abilities).uppercase(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }

        AnimatedVisibility(fetchingAbilities){
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp)
            )
        }
        abilities.forEach { ability ->
            AbilityCard(
                modifier = Modifier.padding(10.dp),
                ability = ability.name.orEmpty(),
                effect = ability.effect.orEmpty()
            )
        }


    }
}