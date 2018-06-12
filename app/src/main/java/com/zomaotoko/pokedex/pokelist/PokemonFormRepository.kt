package com.zomaotoko.pokedex.pokelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.SparseArray
import com.zomaotoko.pokedex.apirequest.buildPokemonFormService
import com.zomaotoko.pokedex.dto.pokedata.PokemonForm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonFormRepository {
    private val service = buildPokemonFormService()
    private val pokemonForms = SparseArray<PokemonForm>()
    private val liveData = MutableLiveData<SparseArray<PokemonForm>>()

    fun askForPokemonForm(id: Int) : PokemonForm? {
        pokemonForms[id]?.let {
            return it
        }

        val call = service.getForm(id)
        call.enqueue(object : Callback<PokemonForm> {
            override fun onResponse(call: Call<PokemonForm>?, response: Response<PokemonForm>?) {
                response?.body()?.let { pokemonForm ->
                    pokemonForms.append(id, pokemonForm)
                    liveData.value = pokemonForms
                }
            }

            override fun onFailure(call: Call<PokemonForm>?, t: Throwable?) {

            }
        })
        return null
    }

    val availableForms: LiveData<SparseArray<PokemonForm>>
        get() = liveData
}