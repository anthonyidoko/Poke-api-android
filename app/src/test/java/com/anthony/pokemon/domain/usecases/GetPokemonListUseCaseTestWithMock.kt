package com.anthony.pokemon.domain.usecases

import com.anthony.pokemon.data.RepositoryImpl
import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTestWithMock {

    @MockK
    lateinit var repository: RepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test get pokemonList limit 5, offset 5 returns 5 pokemons`() = runTest {
        every { repository.getPokemonList(5, 5) } returns flow { emit(Result.Success(
            buildPokemonList().subList(0,5)
        )) }
        val result = GetPokemonListUseCase(repository).invoke(5, 5).getResponseForSuccess()
        assertEquals(result.first(), buildPokemonList().subList(0,5))
    }

    @Test
    fun `test get pokemonList limit 5, offset 600 throws an exception`() = runTest {
        every { repository.getPokemonList(5, 600) } returns flow {
            emit(
                Result.Success(
                    buildPokemonList().subList(600, 600+5)
                )
            )
        }
        val result = GetPokemonListUseCase(repository).invoke(5, 600).getResponseForError()
        assertTrue(result.first() == DataError.Network.UnknownException)

    }

}