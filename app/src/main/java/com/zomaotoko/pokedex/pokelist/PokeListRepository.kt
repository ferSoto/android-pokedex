package com.zomaotoko.pokedex.pokelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.zomaotoko.pokedex.apirequest.buildPokemonService
import com.zomaotoko.pokedex.dto.APIResource
import com.zomaotoko.pokedex.dto.endpoint.PokemonEndPointResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.coroutines.experimental.buildIterator

class PokeListRepository() {
    companion object {
        private const val TAG = "PokéListService"
        private const val ERROR_MESSAGE = "Error while requesting pokémon's list."
    }

    private val pokeListService = buildPokemonService()
    private val pokeList = ArrayList<PokeListElement>()
    private val liveData = MutableLiveData<ArrayList<PokeListElement>>()

    private val index = buildIterator {
        var counter = 1
        while (true) {
            yield(counter ++)
        }
    }

    fun requestPokeList(url: String? = null): LiveData<ArrayList<PokeListElement>> {
        if (url == null && !pokeList.isEmpty()) return liveData

        val call = getPokeListCall(url)
        call.enqueue(object : Callback<PokemonEndPointResponse> {
            override fun onResponse(call: Call<PokemonEndPointResponse>?, response: Response<PokemonEndPointResponse>?) {
                response?.body()?.let { epResponse ->
                    epResponse.results?.let { results ->
                        updateLiveData(results)
                    }

                    if (epResponse.next != null) {
                        requestPokeList(epResponse.next)
                    }
                }
            }

            override fun onFailure(call: Call<PokemonEndPointResponse>?, t: Throwable?) {
                Log.e(TAG, ERROR_MESSAGE)
            }
        })
        return liveData
    }

    private fun updateLiveData(apiResources: ArrayList<APIResource>) {
        pokeList.addAll(apiResources.map {  PokeListElement(index.next(), it) })
        liveData.value = pokeList
    }

    private fun getPokeListCall(url: String?) = if (url == null)
            pokeListService.getEndPointResource()
        else
            pokeListService.getEndPointResource(url)
}
