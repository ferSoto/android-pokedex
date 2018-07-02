package com.zomaotoko.pokedex.pokemon.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.zomaotoko.pokedex.dto.pokedata.Pokemon

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    companion object {
        private const val POKEMON_PROPERTIES = 10
    }

    class ViewHolder(val view: DetailItem) : RecyclerView.ViewHolder(view)

    var pokemon: Pokemon? = null
        set(value) {
            field = value
            if (value != null) notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DetailItem(parent.context))
    }

    override fun getItemCount() = POKEMON_PROPERTIES

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.let {
            when(position) {
                0 -> setNumber(it)
                1 -> setName(it)
                2 -> setHeight(it)
                3 -> setWeight(it)
                4 -> setBaseExperience(it)
                5 -> setTypes(it)
                6 -> setAbilities(it)
                7 -> setMoves(it)
                8 -> setItems(it)
                else -> setSpecies(it)
            }
        }
    }

    private fun setNumber(view: DetailItem) {
        with(view) {
            title = "Number:"
            content = pokemon?.id.toString().capitalize()
        }
    }

    private fun setName(view: DetailItem) {
        with(view) {
            title = "Name:"
            content = pokemon?.name?.capitalize() ?: ""
        }
    }

    private fun setHeight(view: DetailItem) {
        with(view) {
            title = "Height:"
            content = pokemon?.height.toString().capitalize()
        }
    }

    private fun setWeight(view: DetailItem) {
        with(view) {
            title = "Weight:"
            content = pokemon?.weight.toString().capitalize()
        }
    }

    private fun setBaseExperience(view: DetailItem) {
        with(view) {
            title = "Base Experience:"
            content = pokemon?.baseExperience.toString().capitalize()
        }
    }

    private fun setTypes(view: DetailItem) {
        with(view) {
            title = "Types:"
            var content = ""
            pokemon?.types?.let { types ->
                types.forEach {
                    content += it.type?.name?.capitalize()
                    if (it != types.last()) content += System.lineSeparator()
                }
            }
            this.content = content
        }
    }

    private fun setAbilities(view: DetailItem) {
        with(view) {
            title = "Abilities:"
            var content = ""
            pokemon?.abilities?.let { abilities ->
                abilities.forEach {
                    content += it.ability?.name?.capitalize()
                    if (it != abilities.last()) content += System.lineSeparator()
                }
            }
            this.content = content
        }
    }

    private fun setMoves(view: DetailItem) {
        with(view) {
            title = "Moves:"
            var content = ""
            pokemon?.moves?.let { moves ->
                moves.forEach {
                    content += it.move?.name?.capitalize()
                    if (it != moves.last()) content += System.lineSeparator()
                }
            }
            this.content = content
        }
    }

    private fun setItems(view: DetailItem) {
        with(view) {
            title = "Items:"
            var content = ""
            pokemon?.heldItems?.let { items ->
                items.forEach {
                    content += it.item?.name?.capitalize()
                    if (it != items.last()) content += System.lineSeparator()
                }
            }
            this.content = if (content != "") content else "None"
        }
    }

    private fun setSpecies(view: DetailItem) {
        with(view) {
            title = "Species:"
            pokemon?.species?.name?.let {
                this.content = it.capitalize()
            }
        }
    }
}