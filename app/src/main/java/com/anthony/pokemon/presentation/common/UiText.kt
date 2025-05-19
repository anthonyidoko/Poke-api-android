package com.anthony.pokemon.presentation.common

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

sealed interface UiText{
    class StaticText(
        @StringRes val id: Int,
        vararg val args: Any
    ): UiText

    data class DynamicText(
        val value: String
    ): UiText


    @Composable
    fun getString(): String {
        return when(this){
            is StaticText -> stringResource(id, *args)
            is DynamicText -> value
        }

    }
}