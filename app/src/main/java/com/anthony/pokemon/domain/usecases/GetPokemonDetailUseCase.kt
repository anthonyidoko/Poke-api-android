package com.anthony.pokemon.domain.usecases

import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.domain.model.mapToListOfString
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetPokemonDetailUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke(pokemonName: String): Flow<Result<PokemonDetailData, DataError.Network>> =
        flow {
            val result = repository.getPokemonDetails(pokemonName).first()
            if (result is Result.Success) {
                val sprites = result.data.sprites.mapToListOfString()
                emit(Result.Success(result.data.copy(spritesList = sprites)))

            } else {
                emit(result)
            }

        }
}