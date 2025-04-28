package com.anthony.pokemon.presentation.pokemondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anthony.pokemon.R
import com.anthony.pokemon.domain.model.PokemonAbility
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.usecases.GetPokemonDetailUseCase
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.onError
import com.anthony.pokemon.domain.util.onSuccess
import com.anthony.pokemon.presentation.common.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val pokemonDetail = pokemonDetail(savedStateHandle)
    private val _state = MutableStateFlow(PokemonDetailUiState())
    val state = _state
        .onStart { getPokemonDetails() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value
        )

    fun onEvent(event: PokemonDetailEvent) {}

    private fun getPokemonDetails() {
        _state.update { state -> state.copy(name = pokemonDetail.name) }
        viewModelScope.launch {
            getPokemonDetailUseCase.invoke(pokemonDetail.name)
                .onEach { result ->
                    result.onSuccess { response ->
                        viewModelScope.launch { fetPokemonAbilities(response.abilities) }
                        _state.update { state ->
                            state.copy(
                                sprites = response.spritesList,
                                name = response.name.orEmpty(),
                                errorText = null,
                                pokemonDetailData = response
                            )
                        }
                    }.onError { error ->
                        setErrorText(error)
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun fetPokemonAbilities(abilities: List<PokemonAbility>) {
        abilities.forEach { ability ->
            viewModelScope.launch {
                repository.fetchAbility(ability)
                    .onStart {
                        _state.update {
                            it.copy(fetchingAbilities = true)
                        }
                    }
                    .onEach { result ->
                        result.onSuccess { item ->
                            _state.update { state ->
                                state.copy(
                                    abilities = state.abilities + item
                                )
                            }
                        }
                    }
                    .onCompletion { _state.update { it.copy(fetchingAbilities = false) } }
                    .launchIn(viewModelScope)
            }
        }
    }

    private fun setErrorText(error: DataError) {
        val uiText = UiText.StaticText(
            when (error) {
                DataError.Network.UnknownException -> R.string.unknown_error
                DataError.Network.SerializationException -> R.string.serialization_error
                DataError.Network.InvalidRequestObject -> R.string.invalid_request
                DataError.Network.TimeoutException -> R.string.timeout_exception
                else -> R.string.unknown_error
            }
        )
        _state.update {
            it.copy(
                errorText = uiText
            )
        }
    }
}