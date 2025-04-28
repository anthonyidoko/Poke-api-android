package com.anthony.pokemon.data.service

import com.anthony.pokemon.data.dto.AbilityDataDto
import com.anthony.pokemon.data.dto.AbilityDto
import com.anthony.pokemon.data.dto.PokemonDetailResponseDto
import com.anthony.pokemon.data.dto.PokemonDto
import com.anthony.pokemon.data.dto.PokemonResponseDto

interface PokemonService {
    suspend fun getPokemonsFromApi(limit: Int, offset: Int): PokemonResponseDto
    suspend fun getPokemonDetails(name: String): PokemonDetailResponseDto
    suspend fun getPokemonAbility(url: String): AbilityDataDto
}