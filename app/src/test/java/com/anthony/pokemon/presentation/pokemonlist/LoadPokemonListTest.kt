package com.anthony.pokemon.presentation.pokemonlist

import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.model.PokemonAbility
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.usecases.GetPokemonListUseCase
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoadPokemonListTest {

    @Test
    fun initialScreenStateIsDefault() {
        val repository = FakeRepository()
        val useCase = GetPokemonListUseCase(repository)
        val viewModel = PokemonListViewModel(useCase)

        assertEquals(PokemonListUiState(), viewModel.state.value)
    }

    class FakeRepository: PokemonRepository {
        override fun getPokemonList(
            limit: Int,
            offset: Int
        ): Flow<Result<List<Pokemon>, DataError.Network>> {
            TODO("Not yet implemented")
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
}