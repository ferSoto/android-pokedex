package com.zomaotoko.pokedex.dto.pokedata

import com.zomaotoko.pokedex.dto.APIResource

data class PokemonHeldItem(
        var item: APIResource? = null,
        var versionDetails: ArrayList<PokemonHeldItemVersion>? = null
)