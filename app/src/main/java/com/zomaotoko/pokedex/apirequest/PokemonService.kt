package com.zomaotoko.pokedex.apirequest

import com.zomaotoko.pokedex.dto.pokedata.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("$POKEMON_API/{id}/")
    fun getPokemonResource(@Path("id") id: Int): Call<Pokemon>
}