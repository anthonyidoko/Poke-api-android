package com.anthony.pokemon.presentation.common

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText{

    data class StaticText(
        @StringRes val id: Int,
        val args: List<Any> = emptyList(),
    ): UiText

    data class DynamicText(
        val value: String
    ): UiText


    @Composable
    fun getString(): String {
        return when(this){
            is StaticText -> stringResource(id, args)
            is DynamicText -> value
        }

    }
}