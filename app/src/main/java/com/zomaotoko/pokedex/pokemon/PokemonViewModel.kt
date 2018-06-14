package com.zomaotoko.pokedex.pokemon

import android.arch.lifecycle.LiveData
import android.util.SparseArray
import com.zomaotoko.pokedex.dto.pokedata.Pokemon

class PokemonViewModel {
    private val repository = PokemonRepository()

    fun askForPokemon(id: Int): LiveData<SparseArray<Pokemon>> {
        return repository.askForPokemon(id)
    }
}