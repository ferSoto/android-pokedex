package com.zomaotoko.pokedex.pokemon


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomaotoko.pokedex.R
import com.zomaotoko.pokedex.pokemon.recyclerview.DetailAdapter
import com.zomaotoko.pokedex.pokemon.recyclerview.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_pokemon.*

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
    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonId = arguments!!.getInt(POKEMON_KEY, 0)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(PokemonViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(recyclerView) {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        viewModel.askForPokemon(pokemonId).observe(this, Observer {
            detailAdapter.pokemon = it!!.get(pokemonId)
        })
    }
}