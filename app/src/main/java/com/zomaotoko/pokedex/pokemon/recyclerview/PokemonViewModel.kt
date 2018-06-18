package com.zomaotoko.pokedex.pokemon.recyclerview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.SparseArray
import com.zomaotoko.pokedex.dto.pokedata.Pokemon

class PokemonViewModel : ViewModel() {
    private var repository: PokemonRepository? = null

    fun askForPokemon(id: Int): LiveData<SparseArray<Pokemon>> {
        if (repository == null) repository = PokemonRepository()
        return repository!!.askForPokemon(id)
    }
}