package com.zomaotoko.pokedex.dto.pokedata

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class LocationAreaEncounters(
     @SerializedName("location_area") var locationArea: APIResource? = null,
     @SerializedName("version_details") var versionDetails: VersionEncounterDetails? = null
)