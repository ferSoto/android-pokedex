package com.zomaotoko.pokedex.apirequest

import com.zomaotoko.pokedex.dto.endpoint.PokemonEndPointResponse
import com.zomaotoko.pokedex.dto.pokedata.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeListService {
    @GET(POKEMON_API_URL)
    fun getEndPointResource(): Call<PokemonEndPointResponse>

    @GET
    fun getEndPointResource(@Url url: String): Call<PokemonEndPointResponse>

    @GET
    fun getPokemonResource(@Url url: String): Call<Pokemon>
}