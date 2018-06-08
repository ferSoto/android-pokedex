package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class VersionGameIndex(
        @SerializedName("game_index") var gameIndex: Int = 0,
        var version: APIResource? = null
)