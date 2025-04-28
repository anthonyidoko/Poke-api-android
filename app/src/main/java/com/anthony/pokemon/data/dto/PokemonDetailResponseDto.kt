package com.anthony.pokemon.data.dto

import com.anthony.pokemon.domain.model.PokemonAbility
import com.anthony.pokemon.domain.model.PokemonDetailData
import com.anthony.pokemon.domain.model.PokemonSprites
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponseDto(
    val id: Int,
    val name: String,
    val sprites: PokemonSpritesDto,
    val abilities: List<AbilityDto>
)

@Serializable
data class AbilityDto(
    @SerialName("is_hidden")
    val isHidden: Boolean?,
    val slot: Int?,
    val ability: AbilityDataDto
)

fun List<AbilityDto>.mapToDomainAbilities(): List<PokemonAbility>{
    return map { PokemonAbility(name = it.ability.name, url=it.ability.url)  }
}

@Serializable
data class AbilityDataDto(
    val name: String? = null,
    val url: String? = null,
    val effect_entries: List<Effect> = emptyList(),
    val flavor_text_entries: List<Flavour> = emptyList()
)

fun AbilityDataDto.mapToDomainAbility(): PokemonAbility{
    return PokemonAbility(
        effect = effect_entries.find { it.language?.name?.contains("en")==true }?.effect.orEmpty(),
        flavour = flavor_text_entries.find { it.language?.name?.contains("en")==true }?.flavor_text.orEmpty()
    )
}

@Serializable
data class Effect(
    val effect: String?,
    val language: Language?,
    val short_effect: String?
)

@Serializable
data class Language(
    val name: String?
)
@Serializable
data class Flavour(
    val flavor_text: String?,
    val language: Language?
)


fun PokemonDetailResponseDto.mapToDomainDetailData(): PokemonDetailData{
    return PokemonDetailData(
        id = id,
        name = name,
        sprites = sprites.mapToDomainSprite(),
        abilities = abilities.mapToDomainAbilities()
    )
}

@Serializable
data class PokemonSpritesDto(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String?,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String?,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
//    @SerialName("height")
//    val heightInHectograms: Int?,
//    @SerialName("weight")
//    val heightInDecimeter: Int?,
//    val others: PokemonFormDto
)



fun PokemonSpritesDto.mapToDomainSprite(): PokemonSprites {
    return PokemonSprites(
        backShiny = backShiny,
        backFemale = backFemale,
        backDefault = backDefault,
        backShinyFemale = backShinyFemale,
        frontDefault = frontDefault,
        frontShiny = frontShiny,
        frontFemale = frontFemale,
        frontShinyFemale = frontShinyFemale
    )
}


@Serializable
data class PokemonFormDto(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String?,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String?,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?
)
