package com.zomaotoko.pokedex.apirequest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildPokemonService() = createRetrofitInstance(POKEAPI_URL).create(PokeListService::class.java)

private fun createRetrofitInstance(baseUrl: String) = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()