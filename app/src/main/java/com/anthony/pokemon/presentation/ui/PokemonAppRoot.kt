package com.anthony.pokemon.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.anthony.pokemon.presentation.ui.theme.PokemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonAppRoot() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    PokemonTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                PokemonTopAppBar(
                    navHostController = navController,
                    scrollBehavior = scrollBehavior,
                )
            }
        ) { innerPadding ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                navController = navController,
                startDestination = PokemonGraph
            ) {
                pokemonNavigation(
                    navController = navController,
                )
            }
        }
    }
}

