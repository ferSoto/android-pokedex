package com.zomaotoko.pokedex.apirequest

import com.zomaotoko.pokedex.dto.pokedata.PokemonForm
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonFormService {
    @GET("$POKEMON_FORM_API/{id}/")
    fun getForm(@Path("id") id: Int): Call<PokemonForm>
}