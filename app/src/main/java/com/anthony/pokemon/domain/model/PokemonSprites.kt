package com.anthony.pokemon.domain.model

data class PokemonSprites(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

fun PokemonSprites?.mapToListOfString(): Map<String, String> {
    return if (this == null) emptyMap() else
        buildMap {
            frontDefault?.let { image ->
                put("Front Default", image)
            }
            backDefault?.let { img ->
                "Back Default" to img
            }
            frontShiny?.let { image ->
                put("Front Shiny", image)
            }
            frontFemale?.let { image ->
                put("Front Female", image)
            }
            frontShinyFemale?.let { image ->
                put("Front shiny Female", image)
            }
            backShiny?.let { image ->
                "Back Shiny" to image
            }
            backShinyFemale?.let { image ->
                put("Back Shiny Female", image)
            }
            backFemale?.let { img ->
                put("Back Female", img)
            }
        }
}
