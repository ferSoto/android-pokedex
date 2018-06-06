package com.zomaotoko.pokedex.pokelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zomaotoko.pokedex.R

class PokeListAdapter(private var dataset: ArrayList<String>) : RecyclerView.Adapter<PokeListAdapter.ViewHolder>() {
    class ViewHolder(val view: PokeListCell) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PokeListCell(parent.context)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.let {
            it.name = dataset[position]
            it.number = position.toString()
            it.image = it.context.getDrawable(R.drawable.ic_pokeball)
            it.zoomContainer = zoomContainer
        }
    }

    var zoomContainer: ImageView? = null
        set(imageView) {
            imageView?.let {
                field = it
                notifyDataSetChanged()
            }
        }
}