package com.anthony.pokemon.presentation.pokemondetails.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.anthony.pokemon.presentation.pokemonlist.components.PokemonImage
import com.anthony.pokemon.presentation.ui.theme.PokemonTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonSpritesScreen(
    modifier: Modifier = Modifier,
    sprites: Map<String, String>
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        maxItemsInEachRow = 2
    ) {
        for (entry in sprites) {
            Card(
                modifier = modifier
                    .sizeIn(
                        maxWidth = 170.dp,
                        minWidth = 100.dp
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
            ) {
                PokemonImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)),
                    url = entry.value,
                    pokemonName = entry.key
                )
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = entry.key,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }


        }

    }

}