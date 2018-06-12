package com.zomaotoko.pokedex.pokelist.items

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.zomaotoko.pokedex.R

import kotlinx.android.synthetic.main.layout_poke_list_cell.view.*

class PokeListItem : LinearLayout {

    init {
        inflateLayout()
    }

    private fun inflateLayout() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_poke_list_cell, this)
    }

    var name: String = ""
        set(name) {
            field = name
            nameTxt.text = name
        }

    var number: String = "0"
        set(number) {
            field = number
            numberTxt.text = "#$number"
        }

    var image: ImageView? = null
        get() = zoomableImageView
        set(image) {
            field = image
            image?.let { zoomableImageView.background = it.background }
        }

    var zoomContainer: ImageView? = null
        set(imageView) {
            if (imageView != null) {
                zoomableImageView.enlargedImageView = imageView
            }
        }


    // Constructors (all empty)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)
}