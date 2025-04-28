package com.anthony.pokemon.presentation.pokemonlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.anthony.pokemon.R
import com.anthony.pokemon.presentation.common.IndicatorSize
import com.anthony.pokemon.presentation.common.ProgressIndicator

@Composable
fun PokemonImage(
    modifier: Modifier = Modifier,
    url: String,
    pokemonName: String
    ) {
    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        var imageResult by remember {
            mutableStateOf<Result<Painter>?>(null)
        }
        val imagePainter = rememberAsyncImagePainter(
            model = url,
            onSuccess = {
                val size = it.painter.intrinsicSize
                imageResult = if (size.width > 1 && size.height > 1) {
                    Result.success(it.painter)
                } else {
                    Result.failure(Throwable())
                }
            },
            onError = {
                imageResult = Result.failure(it.result.throwable)
            },
        )
        when (val result = imageResult) {
            null -> {
                Box(
                    modifier = Modifier.size(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ProgressIndicator(
                        IndicatorSize.SMALL,
                        trackColor = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            else -> {
                Image(
                    modifier = Modifier
                        .sizeIn(
                            maxWidth = 250.dp
                        )
                        .aspectRatio(1F),
                    painter = if (result.isSuccess) {
                        imagePainter
                    } else {
                        painterResource(R.drawable.the_pokemon_world)
                    }, contentDescription = pokemonName,
                    contentScale = ContentScale.Crop
                )
            }

        }
    }
}