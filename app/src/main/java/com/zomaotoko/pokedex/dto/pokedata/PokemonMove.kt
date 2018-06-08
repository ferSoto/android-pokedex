package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class PokemonMove(
        var move: APIResource? = null,
        @SerializedName("version_group_details") var versionGroupDetails: ArrayList<PokemonMoveVersion>? = null
)