package com.anthony.pokemon.presentation.pokemonlist

sealed interface PokemonListUiEvent {
    data object LoadMore: PokemonListUiEvent
    data class OnPokemonClick(val name: String): PokemonListUiEvent
}