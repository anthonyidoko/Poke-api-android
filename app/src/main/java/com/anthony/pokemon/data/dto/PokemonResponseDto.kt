package com.anthony.pokemon.data.dto

import com.anthony.pokemon.domain.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponseDto(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    @SerialName("results")
    val results: List<PokemonDto>
)

