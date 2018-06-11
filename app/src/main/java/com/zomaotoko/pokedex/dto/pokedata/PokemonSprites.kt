package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName

data class PokemonSprites(
        @SerializedName("back_shiny_female") var backShinyFemale: String? = null,
        @SerializedName("back_default") var backDefault: String? = null,
        @SerializedName("front_female") var frontFemale: String? = null,
        @SerializedName("front_shiny_female") var frontShinyFemale: String? = null,
        @SerializedName("back_shiny") var backShiny: String? = null,
        @SerializedName("front_default") var frontDefault: String? = null,
        @SerializedName("front_shiny") var frontShiny: String? = null
)
