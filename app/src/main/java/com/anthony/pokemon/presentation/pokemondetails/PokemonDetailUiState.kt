package com.anthony.pokemon.presentation.pokemondetails

import com.anthony.pokemon.domain.model.PokemonAbility
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.presentation.common.UiText

data class PokemonDetailUiState(
    val isLoadingDetails: Boolean = false,
    val errorText: UiText? = null,
    val name: String = "",
    val pokemonDetailData: PokemonDetailData? = null,
    val sprites: Map<String,String> = emptyMap(),
    val abilities: List<PokemonAbility> = emptyList(),
    val fetchingAbilities: Boolean = true
)
