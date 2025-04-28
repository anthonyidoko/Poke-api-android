package com.anthony.pokemon.data.repository

import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRepository: PokemonRepository {
    private val pokemonList = buildList {
        addAll(
            (0..50).map { index ->
                Pokemon(
                    name = "Poke $index",
                    detailUrl = ""
                )
            }
        )
    }
    override fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Result<List<Pokemon>, DataError.Network>> = flow{
//        try {
            val l = pokemonList.subList(offset, offset+limit)
            emit(Result.Success(l))
//        }catch (e: Exception){
//            emit(Result.Error(DataError.Network.UnknownException))
//        }
    }

    override fun getPokemonDetails(name: String): Flow<Result<PokemonDetailData, DataError.Network>> {
        TODO("Not yet implemented")
    }

    override fun getFavouritePokemonList(): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }
}