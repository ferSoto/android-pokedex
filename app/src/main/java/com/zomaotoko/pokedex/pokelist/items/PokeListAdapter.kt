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

class PokeListAdapter(fragment: Fragment, private val activity: FragmentActivity) : RecyclerView.Adapter<PokeListAdapter.ViewHolder>() {
    class ViewHolder(val view: PokeListItem) : RecyclerView.ViewHolder(view)

    private var currentId: Int = 0
    private var dataSet: ArrayList<PokeListElement> = ArrayList()
    private lateinit var currentImageView: ImageView
    private var viewModel: PokeListItemViewModel = ViewModelProviders.of(fragment).get(PokeListItemViewModel::class.java)

    init {
        viewModel.forms.observe(fragment, Observer {
            viewModel.askForPokemonForm(currentId)?.sprites?.frontDefault?.let {
                Glide.with(activity).load(it).into(currentImageView)
            }
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
            currentId = pokemon.id
            it.name = pokemon.name!!
            it.number = pokemon.id.toString()
            it.zoomContainer = zoomContainer
            currentImageView = it.image!!
            viewModel.askForPokemonForm(currentId)?.sprites?.frontDefault?.let { sprite ->
                Glide.with(activity).load(sprite).into(it.image!!)
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