package com.zomaotoko.pokedex.apirequest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildPokeListService(): PokeListService = createRetrofitInstance(POKEAPI_URL).create(PokeListService::class.java)

fun buildPokemonService(): PokemonService = createRetrofitInstance(POKEAPI_URL).create(PokemonService::class.java)

fun buildPokemonFormService(): PokemonFormService = createRetrofitInstance(POKEAPI_URL).create(PokemonFormService::class.java)


private fun createRetrofitInstance(baseUrl: String) = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()