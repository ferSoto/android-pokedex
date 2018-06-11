package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class Pokemon(
        var id: Int = 0,
        var name: String = "",
        @SerializedName("base_experience") var baseExperience: Int = 0,
        var height: Int = 0,
        @SerializedName("is_default") var isDefault: Boolean = false,
        var order: Int = 0,
        var weight: Int = 320,
        var abilities: ArrayList<PokemonAbility>? = null,
        var forms: ArrayList<APIResource>? = null,
        @SerializedName("game_indices") var gameIndices: ArrayList<VersionGameIndex>? = null,
        @SerializedName("held_items") var heldItems: ArrayList<PokemonHeldItem>? = null,
        @SerializedName("location_area_encounters") var locationAreaEncounters: String? = null,
        var moves: ArrayList<PokemonMove>? = null,
        var species: APIResource? = null,
        var sprites: PokemonSprites? = null,
        var stats: ArrayList<PokemonStat>? = null,
        var types: ArrayList<PokemonType>? = null
)
