package com.anthony.pokemon.presentation.pokemonlist

import com.anthony.pokemon.CoroutineTestExtension
import com.anthony.pokemon.R
import com.anthony.pokemon.collectFlow
import com.anthony.pokemon.domain.model.Pokemon
import com.anthony.pokemon.domain.model.PokemonAbility
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.usecases.GetPokemonListUseCase
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.Result
import com.anthony.pokemon.presentation.common.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineTestExtension::class)
class LoadPokemonListTest {

    private val pikachu = Pokemon("Pikachu", null)
    private val spearow = Pokemon("Spearow", null)
    private val allPokemons = listOf(pikachu, spearow)

    @Test
    fun initialScreenStateIsDefault() {
        val repository = FakeRepository()
        val useCase = GetPokemonListUseCase(repository)
        val viewModel = PokemonListViewModel(useCase)

        assertEquals(PokemonListUiState(), viewModel.state.value)
    }

    @Test
    fun pokemonListIsEmpty() = runTest {
        val repository = FakeRepository()
        val useCase = GetPokemonListUseCase(repository)
        val viewModel = PokemonListViewModel(useCase)

        val actualDeliveredStates = collectFlow(viewModel.state)

        val expectedDeliveredStates = listOf(
            PokemonListUiState(),
            PokemonListUiState(
                loading = false,
                loadingMessage = UiText.StaticText(R.string.loading_pokemon)
            )
        )
        assertEquals(expectedDeliveredStates, actualDeliveredStates)
    }

    @Test
    fun pokemonListLoaded() = runTest {
        val repository = FakeRepository(pokemonList = allPokemons)
        val useCase = GetPokemonListUseCase(repository)
        val viewModel = PokemonListViewModel(useCase)

        val actualDeliveredStates = collectFlow(viewModel.state)

        val expectedDeliveredStates = listOf(
            PokemonListUiState(),
            PokemonListUiState(
                loading = false,
                loadingMessage = UiText.StaticText(R.string.loading_pokemon),
                pokemons = allPokemons
            )
        )
        assertEquals(expectedDeliveredStates, actualDeliveredStates)
    }

    @Test
    fun pokemonLoadingFailure() = runTest {
        val repository = FakeRepository().apply { setIsUnavailable() }
        val useCase = GetPokemonListUseCase(repository)
        val viewModel = PokemonListViewModel(useCase)

        val actualDeliveredStates = collectFlow(viewModel.state)

        val expectedDeliveredStates = listOf(
            PokemonListUiState(),
            PokemonListUiState(
                loading = false,
                loadingMessage = UiText.StaticText(R.string.loading_pokemon),
                errorMessage = UiText.StaticText(R.string.unknown_error)
            )
        )
        assertEquals(expectedDeliveredStates, actualDeliveredStates)
    }


    class FakeRepository(
        private val pokemonList: List<Pokemon> = emptyList()
    ) : PokemonRepository {

        private var isUnavailable = false

        override fun getPokemonList(
            limit: Int,
            offset: Int
        ): Flow<Result<List<Pokemon>, DataError.Network>> {
            if (isUnavailable) {
                return flow { emit(Result.Error(DataError.Network.UnknownException)) }
            }
            return flow { emit(Result.Success(pokemonList)) }
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

        fun setIsUnavailable() {
            isUnavailable = true
        }
    }
}