package com.anthony.pokemon.domain.repository

import com.anthony.pokemon.data.dto.AbilityDto
import com.anthony.pokemon.data.dto.PokemonResponseDto
import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.model.PokemonAbility
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.domain.model.PokemonSprites
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<Result<List<Pokemon>,DataError.Network>>
    fun getPokemonDetails(name: String): Flow<Result<PokemonDetailData,DataError.Network>>
    fun getFavouritePokemonList(): Flow<List<Pokemon>>
    fun fetchAbility(ability: PokemonAbility):Flow<Result<PokemonAbility,DataError.Network>>
}