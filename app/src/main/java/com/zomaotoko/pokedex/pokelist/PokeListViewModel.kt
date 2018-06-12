package com.zomaotoko.pokedex.pokelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

class PokeListViewModel : ViewModel() {
    private val pokeListRepo = PokeListRepository()
    val pokeListElements = pokeListRepo.requestPokeList()
}