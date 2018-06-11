package com.zomaotoko.pokedex.pokelist

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zomaotoko.pokedex.R
import com.zomaotoko.pokedex.dto.pokedata.Pokemon

class PokeListAdapter(private var dataSet: ArrayList<Pokemon> = ArrayList(), val activity: Activity) : RecyclerView.Adapter<PokeListAdapter.ViewHolder>() {
    class ViewHolder(val view: PokeListCell) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PokeListCell(parent.context)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.let {
            val pokemon = dataSet[position]
            it.name = pokemon.name
            it.number = pokemon.id.toString()
            it.zoomContainer = zoomContainer
            Glide.with(activity).load(pokemon.sprites?.frontDefault).into(it.image!!)
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

    fun updateDataSet(dataSet: ArrayList<Pokemon>) {
        this.dataSet = dataSet
        this.dataSet.sortBy { it.id }
        notifyDataSetChanged()
    }
}