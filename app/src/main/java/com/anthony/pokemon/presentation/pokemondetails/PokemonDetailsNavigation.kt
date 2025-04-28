package com.anthony.pokemon.presentation.pokemondetails

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(val name: String)
val pokemonDetail: (SavedStateHandle) -> PokemonDetail = {
    val savedName = requireNotNull(it.get<String>("name"))
    PokemonDetail(name = savedName)
}

fun NavGraphBuilder.pokemonDetailScreen() {
    composable<PokemonDetail> {
        PokemonDetailScreenRoot()
    }
}


fun NavHostController.navigateToPokemonDetailScreen(name: String) {
    navigate(PokemonDetail(name))
}

