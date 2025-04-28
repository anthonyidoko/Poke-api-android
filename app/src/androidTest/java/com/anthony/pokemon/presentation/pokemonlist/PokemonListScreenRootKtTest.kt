package com.anthony.pokemon.presentation.pokemonlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class PokemonListScreenRootKtTest{
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun firstTest(){
        composeTestRule.setContent {
            PokemonListScreenRoot(
                state = PokemonListUiState(),
                showSnackBar = {},
                onEvent = {}
            )
        }
        composeTestRule.onNodeWithText("Loading Pokemon").assertIsDisplayed()
    }
}