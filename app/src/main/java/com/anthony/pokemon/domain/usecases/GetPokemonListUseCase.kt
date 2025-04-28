package com.anthony.pokemon.domain.usecases

import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetPokemonListUseCase(
    private val repository: PokemonRepository
) {

    operator fun invoke(limit: Int = 20, offset: Int = 20): Flow<Result<List<Pokemon>, DataError.Network>> = flow{
        try {
            val result = repository.getPokemonList(limit = limit, offset = offset).first()
            emit(result)
        }catch (e: Exception){
            if (e is CancellationException) throw e
            emit(Result.Error(DataError.Network.UnknownException))
        }
    }
}