package com.zomaotoko.pokedex.pokelist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

class PokeListAdapter(private var dataset: ArrayList<String>) : RecyclerView.Adapter<PokeListAdapter.ViewHolder>() {
    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TextView(parent.context)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataset[position]
    }
}