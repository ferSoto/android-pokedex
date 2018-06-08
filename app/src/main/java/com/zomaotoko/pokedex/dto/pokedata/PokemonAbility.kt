package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class PokemonAbility(
        @SerializedName("is_hidden") var isHidden: Boolean = false,
        var slot: Int = 0,
        var ability: APIResource? = null
)