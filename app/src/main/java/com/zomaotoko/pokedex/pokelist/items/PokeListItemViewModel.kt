package com.zomaotoko.pokedex.pokelist.items

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.SparseArray
import com.zomaotoko.pokedex.dto.pokedata.PokemonForm

class PokeListItemViewModel : ViewModel() {
    private val repository = PokemonFormRepository()

    fun askForPokemonForm(id: Int) = repository.askForPokemonForm(id)

    val forms: LiveData<SparseArray<PokemonForm>>
        get() = repository.availableForms
}