package com.anthony.pokemon.data.dto

import com.anthony.pokemon.domain.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val name: String? = null,
    val url: String? = null,
)

fun PokemonDto.mapDomainPokemon(): Pokemon {
    return Pokemon(
        name = name,
        detailUrl = url
    )
}



