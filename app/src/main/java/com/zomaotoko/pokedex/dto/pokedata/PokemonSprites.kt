package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName

data class PokemonSprites(
        @SerializedName("back_shiny_female") var backShinyFemale: String? = null,
        @SerializedName("back_default") var backDefault: String? = null,
        @SerializedName("front_female") var frontFemale: String? = null,
        @SerializedName("back_female") var backFemale: String? = null,
        @SerializedName("front_shiny_female") var frontShinyFemale: String? = null,
        @SerializedName("back_shiny") var backShiny: String? = null,
        @SerializedName("front_default") var frontDefault: String? = null,
        @SerializedName("front_shiny") var frontShiny: String? = null
) {
    val arrayList: ArrayList<String>
        get() = ArrayList<String>().apply {
            if (frontDefault != null) add(frontDefault!!)
            if (backDefault != null) add(backDefault!!)
            if (frontFemale != null) add(frontFemale!!)
            if (backFemale != null) add(backFemale!!)
            if (frontShiny != null) add(frontShiny!!)
            if (backShiny != null) add(backShiny!!)
            if (frontShinyFemale != null) add(frontShinyFemale!!)
            if (backShinyFemale != null) add(backShinyFemale!!)//*/
        }
}
