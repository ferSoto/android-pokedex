package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class PokemonForm(
        var id: Int = 0,
        var name: String? = null,
        var names: ArrayList<String>? = null,
        var pokemon: APIResource? = null,
        var order: Int = 0,
        @SerializedName("is_default") var isDefault: Boolean = false,
        @SerializedName("form_name") var formName: String? = null,
        @SerializedName("form_names") var formNames: String? = null,
        @SerializedName("is_mega") var isMega: Boolean= false,
        @SerializedName("form_order") var formOrder: Int = 0,
        @SerializedName("version_group") var versionGroup: APIResource? = null,
        var sprites: PokemonSprites? = null,
        @SerializedName("is_battle_only") var isBattleOnly: Boolean = false
)