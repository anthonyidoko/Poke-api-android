package com.anthony.pokemon.presentation.pokemonlist

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anthony.pokemon.data.repository.FakePokemonRepository
import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.usecases.GetPokemonListUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class PokemonListViewModelTest {
    private val pokemons = (0..100).map {
        Pokemon(name = "Pokemon $it", detailUrl = null)
    }


    @Test
    fun verifyGetPokemonsCalled() = runTest {
        val repository: PokemonRepository = FakePokemonRepository(pokemons)
        val getPokemonListUseCase = GetPokemonListUseCase(repository)
        val viewModel = PokemonListViewModel(getPokemonListUseCase)

        //Not sure what action to perform in order to trigger the private getPokemons Call in the viewModel


        assertEquals(viewModel.state.value.pokemons.size, 20)
    }
}