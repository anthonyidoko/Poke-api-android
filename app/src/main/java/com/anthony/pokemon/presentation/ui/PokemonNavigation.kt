package com.anthony.pokemon.presentation.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.anthony.pokemon.presentation.pokemondetails.navigateToPokemonDetailScreen
import com.anthony.pokemon.presentation.pokemondetails.pokemonDetailScreen
import com.anthony.pokemon.presentation.pokemonlist.PokemonList
import com.anthony.pokemon.presentation.pokemonlist.pokemonListScreen
import kotlinx.serialization.Serializable

@Serializable
data object PokemonGraph
fun NavGraphBuilder.pokemonNavigation(
    navController: NavHostController,
){
    navigation<PokemonGraph>(
        startDestination = PokemonList
    ){
        pokemonListScreen(
            showSnackBar = { _ -> },
            navigateToDetail = { navController.navigateToPokemonDetailScreen(it) }
        )
        pokemonDetailScreen()
    }
}