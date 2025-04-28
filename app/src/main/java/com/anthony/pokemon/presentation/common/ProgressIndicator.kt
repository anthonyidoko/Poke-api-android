package com.anthony.pokemon.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(
    size: IndicatorSize,
    trackColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    CircularProgressIndicator(
        modifier = Modifier.size(size.value),
        trackColor = trackColor
    )

}

enum class IndicatorSize(val value: Dp) {
    SMALL(30.dp),
    MEDIUM(45.dp),
    LARGE(60.dp)
}