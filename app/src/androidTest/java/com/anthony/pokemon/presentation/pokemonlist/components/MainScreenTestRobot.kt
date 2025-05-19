package com.anthony.pokemon.presentation.pokemonlist.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.anthony.pokemon.R
import com.anthony.pokemon.presentation.ui.MainActivity

class MainScreenTestRobot(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    private val context = rule.activity.applicationContext
    private val pokemonCardTag = context.getString(R.string.pokemon_card_test_tag)

    fun findPokemonCard() {
        rule.onNodeWithTag(pokemonCardTag)
            .assertIsDisplayed()
    }

    fun performClick() {
        rule.onNodeWithTag(pokemonCardTag)
            .performClick()
    }

    infix fun verify(action: MainScreenValidation.()->Unit): MainScreenValidation {
        return MainScreenValidation(rule).apply{action()}
    }

}