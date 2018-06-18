package com.zomaotoko.pokedex.pokemon


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomaotoko.pokedex.R
import com.zomaotoko.pokedex.pokemon.recyclerview.DetailAdapter
import com.zomaotoko.pokedex.pokemon.recyclerview.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_poke_list.*

class PokemonFragment : Fragment() {
    companion object {
        private const val POKEMON_KEY = "pokemon_id"

        fun getInstance(id: Int) : PokemonFragment{
            val fragment = PokemonFragment()
            fragment.arguments = Bundle().apply {
                putInt(POKEMON_KEY, id)
            }
            return fragment
        }
    }

    private var pokemonId: Int = 0
    private val detailAdapter = DetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonId = arguments!!.getInt(POKEMON_KEY, 0)
        val viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        viewModel.askForPokemon(id).observe(this, Observer {
            it!![id]?.let {
                detailAdapter.pokemon = it
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_pokemon, container, false)
        with(recyclerView) {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        return view
    }

}