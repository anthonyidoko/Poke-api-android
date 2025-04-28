package com.anthony.pokemon.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthony.pokemon.R
import com.anthony.pokemon.domain.usecases.GetPokemonListUseCase
import com.anthony.pokemon.domain.util.DataError
import com.anthony.pokemon.domain.util.onError
import com.anthony.pokemon.domain.util.onSuccess
import com.anthony.pokemon.presentation.common.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonListUiState())
    val state = _uiState.onStart {
            fetchPokemons()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _uiState.value
        )

    fun onEvent(event: PokemonListUiEvent) {
        when (event) {
            PokemonListUiEvent.LoadMore -> loadMorePokemons()
            is PokemonListUiEvent.OnPokemonClick -> {}
        }
    }

    private fun fetchPokemons() {
        getPokemonListUseCase.invoke().onStart {
                _uiState.update { state ->
                    state.copy(
                        loading = true,
                        loadingMessage = UiText.StaticText(R.string.loading_pokemon),
                    )
                }
            }.onEach {
                it.onSuccess { data ->
                    _uiState.update { state ->
                        state.copy(
                            pokemons = data,
                            loading = false

                        )
                    }
                }.onError { error ->
                    val uiText = mapDataErrorToUiText(error)
                    _uiState.update { state ->
                        state.copy(
                            errorMessage = uiText,
                            loading = false

                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun mapDataErrorToUiText(error: DataError.Network) = when (error) {
        DataError.Network.UnknownException -> {
            UiText.StaticText(R.string.unknown_error)
        }

        DataError.Network.SerializationException -> {
            UiText.StaticText(R.string.serialization_error)
        }

        DataError.Network.InvalidRequestObject -> {
            UiText.StaticText(R.string.invalid_request)
        }

        DataError.Network.TimeoutException -> {
            UiText.StaticText(R.string.timeout_exception)
        }
    }


    private fun loadMorePokemons() {
        val currentSize = _uiState.value.pokemons.size
        getPokemonListUseCase.invoke(offset = currentSize + currentSize)
            .onStart {
                _uiState.update { state ->
                    state.copy(
                        shouldLoadMore = true,
                    )
                }
            }
            .onEach {
                it.onSuccess { result ->
                    if (result.isNotEmpty()) {
                        _uiState.update { state ->
                            state.copy(
                                shouldLoadMore = false,
                                pokemons = state.pokemons + result,
                                loadMoreMessage = null
                            )
                        }
                    } else {
                        _uiState.update { state ->
                            state.copy(
                                shouldLoadMore = false,
                                loadMoreMessage = UiText.StaticText(R.string.no_more_data_to_load)

                            )
                        }
                    }
                }.onError {
                    _uiState.update { state ->
                        state.copy(
                            shouldLoadMore = false,
                            loadMoreMessage = UiText.StaticText(R.string.an_error_occurred_while_loading_data)
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}