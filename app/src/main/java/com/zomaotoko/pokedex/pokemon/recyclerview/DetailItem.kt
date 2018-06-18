package com.zomaotoko.pokedex.pokemon.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.layout_pokemon_details.view.*

class DetailItem : RelativeLayout {

    var title: String = ""
        set(value) {
            field = value
            titleTxt.text = field
        }

    var content: String = ""
        set(value) {
            field = value
            contentTxt.text = field
        }

    // Constructors (all empty)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)
}