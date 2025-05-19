package com.anthony.pokemon.presentation.pokemonlist.components

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.anthony.pokemon.R
import com.anthony.pokemon.presentation.ui.MainActivity

class MainScreenValidation(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>){
    private val context = rule.activity.applicationContext

    fun titleIsPokemons(){
        val pokemons = context.getString(R.string.pokemon_list)
        rule.onNodeWithText(pokemons)
            .assertExists()
    }

    fun loadingPokemonTextWasDisplayed() {
        val loadingText = context.getString(R.string.loading_pokemon)
        rule.onNodeWithText(loadingText)
    }

    fun dialogWasDisplayed() {
        val dialogTestTag = context.getString(R.string.loading_dialog_tag)
        rule.onNodeWithTag(dialogTestTag)
            .assertExists()
    }

    fun navigatedToDetailsScreen() {
        val detailTitleTag = context.getString(R.string.pokemon_card_test_tag_click)
        rule.onNodeWithTag(detailTitleTag)
            .assertExists()
    }
}