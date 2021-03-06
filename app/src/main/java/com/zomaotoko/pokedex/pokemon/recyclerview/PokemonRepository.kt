package com.zomaotoko.pokedex.pokemon.recyclerview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.SparseArray
import com.zomaotoko.pokedex.apirequest.buildPokemonService
import com.zomaotoko.pokedex.dto.pokedata.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {
    private val pokemonArray = SparseArray<Pokemon>()
    private val liveData = MutableLiveData<Pokemon>()


    fun askForPokemon(id: Int): LiveData<Pokemon> {
        if (pokemonArray[id] != null) {
            liveData.value = pokemonArray[id]
            return liveData
        }

        val service = buildPokemonService()
        val call = service.getPokemonResource(id)
        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>?) {
                val pokemon = response?.body()
                if (pokemon != null) {
                    pokemonArray.put(id, pokemon)
                    liveData.value = pokemon
                }
            }

            override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
            }
        })
        return liveData
    }
}