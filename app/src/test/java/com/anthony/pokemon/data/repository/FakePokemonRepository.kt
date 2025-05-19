package com.anthony.pokemon.data.repository

import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.model.PokemonAbility
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRepository(private val pokemonList: List<Pokemon>) : PokemonRepository {

    override fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Result<List<Pokemon>, DataError.Network>> = flow{
            if (offset+limit > pokemonList.size){
                emit(Result.Error(DataError.Network.UnknownException))
                return@flow
            }
            val pokemons = pokemonList.subList(offset, offset+limit)
            emit(Result.Success(pokemons))
    }

    override fun getPokemonDetails(name: String): Flow<Result<PokemonDetailData, DataError.Network>> {
        TODO("Not yet implemented")
    }

    override fun getFavouritePokemonList(): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }

    override fun fetchAbility(ability: PokemonAbility): Flow<Result<PokemonAbility, DataError.Network>> {
        TODO("Not yet implemented")
    }
}