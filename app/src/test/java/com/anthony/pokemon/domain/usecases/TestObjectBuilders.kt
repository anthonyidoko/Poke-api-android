package com.anthony.pokemon.domain.usecases

import com.anthony.pokemon.domain.model.Pokemon

internal fun buildPokemonList(): List<Pokemon>{
    return (1..500).map {
        Pokemon(
            name = "Poke $it",
            detailUrl = ""
        )
    }
}