package com.zomaotoko.pokedex.pokemon.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.zomaotoko.pokedex.R
import kotlinx.android.synthetic.main.layout_pokemon_details.view.*

class DetailItem : RelativeLayout {
    private lateinit var titleTxt: TextView
    private lateinit var contentTxt: TextView

    init {
        inflateLayout()
    }

    private fun inflateLayout() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_pokemon_details, null)
        titleTxt = view.findViewById(R.id.title_txt)
        contentTxt = view.findViewById(R.id.content_txt)
    }

    var title: String = ""
        set(value) {
            field = value
            titleTxt.text = value
        }

    var content: String = ""
        set(value) {
            field = value
            contentTxt.text = value
        }

    // Constructors (all empty)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)
}