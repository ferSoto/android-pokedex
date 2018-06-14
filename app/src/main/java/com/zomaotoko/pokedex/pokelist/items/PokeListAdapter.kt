package com.zomaotoko.pokedex.pokelist.items

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zomaotoko.pokedex.pokelist.PokeListElement

class PokeListAdapter(private val fragment: Fragment, private val listener: ItemClickListener) : RecyclerView.Adapter<PokeListAdapter.ViewHolder>() {
    class ViewHolder(val view: PokeListItem) : RecyclerView.ViewHolder(view)
    interface ItemClickListener {
        fun onItemClick(id: Int)
    }

    private var dataSet: ArrayList<PokeListElement> = ArrayList()
    private var viewModel: PokeListItemViewModel = ViewModelProviders.of(fragment).get(PokeListItemViewModel::class.java)

    init {
        viewModel.forms.observe(fragment, Observer {
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PokeListItem(parent.context)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.let {
            val pokemon = dataSet[position]
            val pokemonID = pokemon.id
            it.name = pokemon.name!!
            it.number = pokemonID.toString()
            it.zoomContainer = zoomContainer
            viewModel.askForPokemonForm(pokemonID)?.sprites?.frontDefault?.let { sprite ->
                Glide.with(fragment.activity as FragmentActivity).load(sprite).into(it.image!!)
            }
            it.setOnClickListener {
                listener.onItemClick(pokemonID)
            }
            it.invalidate()
        }
    }

    var zoomContainer: ImageView? = null
        set(imageView) {
            imageView?.let {
                field = it
                notifyDataSetChanged()
            }
        }

    fun updateDataSet(dataSet: ArrayList<PokeListElement>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}