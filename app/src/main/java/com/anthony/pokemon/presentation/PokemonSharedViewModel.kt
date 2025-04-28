package com.anthony.pokemon.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PokemonSharedViewModel: ViewModel() {

    private val _state = MutableStateFlow(PokemonSharedUiState())
    val state = _state.asStateFlow()

    fun setName(value: String){
        _state.update {
            it.copy(name = value)
        }
    }

}