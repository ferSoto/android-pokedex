package com.zomaotoko.pokedex.pokelist

import com.zomaotoko.pokedex.dto.APIResource

data class PokeListElement(var id: Int = 0, var apiResource: APIResource? = null, var spriteUrl: String? = null) {
    val name: String?
        get() = apiResource?.name

    val pokemonUrl: String?
        get() = apiResource?.url
}