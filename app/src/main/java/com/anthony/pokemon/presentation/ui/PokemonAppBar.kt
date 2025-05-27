package com.anthony.pokemon.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.toRoute
import com.anthony.pokemon.R
import com.anthony.pokemon.presentation.common.UiText
import com.anthony.pokemon.presentation.pokemondetails.PokemonDetail
import com.anthony.pokemon.presentation.pokemonlist.PokemonList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navHostController: NavController
    ){
    var topBarUiText: UiText by remember {
        mutableStateOf(UiText.StaticText(R.string.empty))
    }

    navHostController.addOnDestinationChangedListener { controller, destination, _ ->
        val title = destination.getTopAppBarUiText(controller)
        topBarUiText = title
    }

    TopAppBar(
        title = { Text(text = topBarUiText.getString()) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            val previousRoute = navHostController.previousBackStackEntry?.destination?.route
            if (previousRoute != null) {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.action_back),
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}



private fun NavDestination.getTopAppBarUiText(
    controller: NavController,
): UiText {
    return when {
        hasRoute<PokemonList>() -> UiText.StaticText(R.string.pokemon_list)
        hasRoute<PokemonDetail>() -> {
            val args = controller.currentBackStackEntry?.toRoute<PokemonDetail>()
            val name = args?.name.orEmpty()
            UiText.StaticText(R.string.pokemon_details_title, listOf(name))
        }
        else -> UiText.StaticText(R.string.empty)
    }

}