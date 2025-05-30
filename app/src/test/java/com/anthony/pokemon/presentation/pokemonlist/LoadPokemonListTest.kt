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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineTestExtension::class)
class LoadPokemonListTest {

    @Test
    fun initialScreenStateIsDefault() {
        val repository = FakeRepository()
        val useCase = GetPokemonListUseCase(repository)
        val viewModel = PokemonListViewModel(useCase)

        assertEquals(PokemonListUiState(), viewModel.state.value)
    }

    @Test
    fun initialPokemonLoad() = runTest {
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


    class FakeRepository: PokemonRepository {

        private var isUnavailable = false

        override fun getPokemonList(
            limit: Int,
            offset: Int
        ): Flow<Result<List<Pokemon>, DataError.Network>> {
            if (isUnavailable) {
                return flow { emit(Result.Error(DataError.Network.UnknownException)) }
            }
            val data = emptyList<Pokemon>()
            return flow { emit(Result.Success(data)) }
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