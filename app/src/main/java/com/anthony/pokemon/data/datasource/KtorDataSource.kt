package com.anthony.pokemon.data.datasource

import com.anthony.pokemon.data.dto.AbilityDataDto
import com.anthony.pokemon.data.dto.AbilityDto
import com.anthony.pokemon.data.dto.PokemonDetailResponseDto
import com.anthony.pokemon.data.dto.PokemonResponseDto
import com.anthony.pokemon.data.service.PokemonService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class KtorDataSource(private val client: HttpClient): PokemonService {
    override suspend fun getPokemonsFromApi(limit: Int, offset: Int): PokemonResponseDto {
        val response: HttpResponse = client.get(
            "https://pokeapi.co/api/v2/pokemon/?limit=$limit&offset=$offset")
        return response.body()
    }

    override suspend fun getPokemonDetails(name: String): PokemonDetailResponseDto {
        val url = "https://pokeapi.co/api/v2/pokemon/$name/"
        val response = client.get(url)
        return response.body()
    }

    override suspend fun getPokemonAbility(url: String): AbilityDataDto {
        val response = client.get(url)
        return response.body()
    }
}