package com.anthony.pokemon.presentation.pokemondetails

sealed interface PokemonDetailEvent {
    data class LoadDetails(val name: String): PokemonDetailEvent
}