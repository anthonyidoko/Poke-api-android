package com.anthony.pokemon.domain.model

data class PokemonDetailData(
    val id: Int? = null,
    val name: String? = null,
    val sprites: PokemonSprites? = null,
    val spritesList: Map<String, String> = emptyMap(),
    val abilities: List<PokemonAbility> = emptyList()
)
