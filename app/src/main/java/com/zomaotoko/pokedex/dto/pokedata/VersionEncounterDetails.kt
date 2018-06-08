package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class VersionEncounterDetails(
        var version: APIResource? = null,
        @SerializedName("max_chance") var maxChance: Int = 0,
        @SerializedName("encounter_details") var encounterDetails: ArrayList<Encounter>? = null
)