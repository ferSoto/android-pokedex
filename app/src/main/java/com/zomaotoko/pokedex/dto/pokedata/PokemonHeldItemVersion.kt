package com.zomaotoko.pokedex.dto.pokedata

import com.zomaotoko.pokedex.dto.APIResource

data class PokemonHeldItemVersion(
        var version: APIResource? = null,
        var rarity: Int = 0
)