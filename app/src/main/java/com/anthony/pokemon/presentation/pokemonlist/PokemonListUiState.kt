package com.anthony.pokemon.presentation.pokemonlist

import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.presentation.common.UiText

data class PokemonListUiState(
    val loadingMessage: UiText? = null,
    val shouldLoadMore: Boolean = false,
    val loadMoreMessage: UiText? = null,
    val loading: Boolean = true,
    val pokemons: List<Pokemon> = emptyList(),
    val errorMessage: UiText? = null
)

