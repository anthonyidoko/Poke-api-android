package com.anthony.pokemon.presentation.pokemondetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AbilityCard(
    modifier: Modifier = Modifier,
    ability: String,
    effect: String
) {
    Column(modifier = modifier) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(modifier = Modifier.padding(10.dp), text = ability)
        }

        Text(
            text = effect,
            textAlign = TextAlign.Start
        )

    }
}