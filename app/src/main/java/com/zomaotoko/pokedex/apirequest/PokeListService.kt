package com.zomaotoko.pokedex.apirequest

import com.zomaotoko.pokedex.dto.endpoint.PokemonEndPointResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeListService {
    @GET(POKEMON_LIST_API)
    fun getEndPointResource(): Call<PokemonEndPointResponse>

    @GET
    fun getEndPointResource(@Url url: String): Call<PokemonEndPointResponse>
}