package com.zomaotoko.pokedex.pokelist

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.zomaotoko.pokedex.R

import kotlinx.android.synthetic.main.layout_poke_list_cell.*
import kotlinx.android.synthetic.main.layout_poke_list_cell.view.*

class PokeListCell : LinearLayout {

    init {
        inflateLayout()
    }

    private fun inflateLayout() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_poke_list_cell, this)
    }

    var name : String = ""
        set(name) {
            field = name
            nameTxt.text = name
        }

    var number : String = "0"
        set(number) {
            field = number
            numberTxt.text = "#$number"
        }

    var image : Drawable? = null
        set(image) {
            field = image
            image?.let { imageView.background = it }
        }

    // Constructors (all empty)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
}