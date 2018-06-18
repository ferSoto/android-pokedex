package com.zomaotoko.pokedex.pokemon.recyclerview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.SparseArray
import com.zomaotoko.pokedex.dto.pokedata.Pokemon
import com.zomaotoko.pokedex.pokemon.PokemonRepository

class PokemonViewModel : ViewModel() {
    private val repository = PokemonRepository()

    fun askForPokemon(id: Int): LiveData<SparseArray<Pokemon>> {
        return repository.askForPokemon(id)
    }
}