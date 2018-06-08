package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class PokemonMoveVersion(
        @SerializedName("move_learn_method") var moveLearnMethod: APIResource? = null,
        @SerializedName("version_group") var versionGroup: APIResource? = null,
        @SerializedName("level_learned_at") var levelLearnedAt: Int = 0
)