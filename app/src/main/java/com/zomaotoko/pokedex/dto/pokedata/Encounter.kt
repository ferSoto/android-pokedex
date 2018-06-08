package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class Encounter(
        @SerializedName("min_level") var minLevel: Int = 0,
        @SerializedName("max_level") var maxLevel: Int = 0,
        @SerializedName("condition_values") var conditionValues: APIResource? = null,
        var chance: Int = 0,
        var method: APIResource? = null
)