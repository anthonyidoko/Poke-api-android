package com.anthony.pokemon.presentation.pokemonlist.components

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.anthony.pokemon.presentation.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class PokemonListScreenKtTest {
    @get:Rule
    val mainscreenRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testPokemonListScreen() {
        launchPokemonApp(mainscreenRule) {
        } verify {
            titleIsPokemons()
        }
    }

    @Test
    fun testLoadingDialogDisplay() {
        launchPokemonApp(mainscreenRule) {
        } verify {
            dialogWasDisplayed()
            loadingPokemonTextWasDisplayed()
        }
    }

    @Test
    fun testClickEvent() {
        launchPokemonApp(mainscreenRule) {
            findPokemonCard()
            performClick()
        } verify {
            navigatedToDetailsScreen()
        }
    }


    private fun launchPokemonApp(
        rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
        block: MainScreenTestRobot.() -> Unit
    ): MainScreenTestRobot {
        return MainScreenTestRobot(rule).apply {
            block()
        }
    }
}

