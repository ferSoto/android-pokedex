package com.zomaotoko.pokedex.dto.pokedata

import com.zomaotoko.pokedex.dto.APIResource

data class PokemonType(
    var slot: Int = 0,
    var type: APIResource? = null
)