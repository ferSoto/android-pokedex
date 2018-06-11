package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class PokemonStat(
        @SerializedName("base_stat") var baseStat: Int = 0,
        var effort: Int = 0,
        var stat: APIResource? = null
)