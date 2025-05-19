package com.anthony.pokemon.data

import com.anthony.pokemon.data.datasource.KtorDataSource
import com.anthony.pokemon.data.dto.mapDomainPokemon
import com.anthony.pokemon.data.dto.mapToDomainAbility
import com.anthony.pokemon.data.dto.mapToDomainDetailData
import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.model.PokemonAbility
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import com.anthony.pokemon.domain.util.safeCall
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val ktorDataSource: KtorDataSource
) : PokemonRepository {
    override fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Result<List<Pokemon>, DataError.Network>> {
        return safeCall(
            block = {
                ktorDataSource.getPokemonsFromApi(limit = limit, offset = offset)
            },
            transform = { item -> item.results.map { it.mapDomainPokemon() } }
        )
    }

    override fun getPokemonDetails(name: String): Flow<Result<PokemonDetailData, DataError.Network>> {
        return safeCall(
            block = { ktorDataSource.getPokemonDetails(name) },
            transform = { it.mapToDomainDetailData() }
        )
    }

    override fun getFavouritePokemonList(): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }

    override fun fetchAbility(
        ability: PokemonAbility
    ): Flow<Result<PokemonAbility, DataError.Network>> {
         return safeCall(
            block = { ktorDataSource.getPokemonAbility(ability.url.orEmpty()) },
            transform = {
                it.mapToDomainAbility().copy(name = ability.name, url = ability.url)
            }
        )
    }
}