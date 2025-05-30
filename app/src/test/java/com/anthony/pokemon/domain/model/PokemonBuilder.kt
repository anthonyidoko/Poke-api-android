package com.anthony.pokemon.domain.model

class PokemonBuilder {

    private var name: String? = null
    private var detailUrl: String? = null

    fun withName(name: String) = apply {
        this.name = name
    }

    fun withDetailUrl(detailUrl: String) = apply {
        this.detailUrl = detailUrl
    }

    fun build() = Pokemon(name, detailUrl)

    companion object {
        fun aPokemon() = PokemonBuilder()
    }
}