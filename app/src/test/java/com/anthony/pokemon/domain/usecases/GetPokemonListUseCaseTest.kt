package com.anthony.pokemon.domain.usecases

import com.anthony.pokemon.data.repository.FakePokemonRepository
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.onError
import com.anthony.pokemon.domain.util.onSuccess
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTest{
    private lateinit var repository: PokemonRepository

    @Before
    fun setUp(){
        repository = FakePokemonRepository()
    }

    @Test
    fun `get pokemons with offset 20, limit 20 returns the first 20 pokemons`(): Unit = runBlocking{
            repository.getPokemonList(20, 20).first()
            .onSuccess {
                assertTrue(it.size == 20)
            }
    }

    @Test
    fun `get pokemons with offset 20, limit 20 should not contain pokemon 40`(): Unit = runBlocking{
        repository.getPokemonList(20,20).first()
            .onSuccess { result ->
                val pokemon = result.find { item -> item.name == "Poke 40" }
                assertTrue(pokemon == null)
            }
    }

//    @Test
//    fun `get pokemons with offset 40, limit 20 should contain Poke 60`(): Unit = runBlocking {
//        repository.getPokemonList(limit = 20, offset = 40).first()
//            .onSuccess { result ->
//                val pokemon = result.find { item -> item.name == "Poke 40" }
//                assertTrue(pokemon != null)
//            }
//    }

//    @Test
//    fun `get pokemons with offset 60, limit 20 returns empty list`(): Unit = runBlocking {
//        repository.getPokemonList(offset = 60, limit = 20).first()
//            .onSuccess { result ->
//                assertTrue(result.isEmpty())}
//            .onError { error ->
//                assertTrue(error == DataError.Network.UnknownException)
//            }
//    }
}