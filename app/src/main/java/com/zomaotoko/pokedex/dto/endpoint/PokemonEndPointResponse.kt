package com.zomaotoko.pokedex.dto.endpoint

import com.zomaotoko.pokedex.dto.APIResource

data class PokemonEndPointResponse(
        var count: Int = 0,
        var previous: String? = null,
        var next: String? = null,
        var results: ArrayList<APIResource>? = null
)