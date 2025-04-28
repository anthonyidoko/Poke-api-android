package com.anthony.pokemon.domain.model


data class Pokemon(
    val name: String?,
    val detailUrl: String?
)

fun Pokemon.getImageUrl(): String {
    val itemsList = detailUrl?.split("/")
    val id = itemsList?.get(itemsList.lastIndex-1)
    return if (id == null) {
        ""
    } else {
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }

}
