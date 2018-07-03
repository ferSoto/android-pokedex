package com.zomaotoko.pokedex.pokemon.slideshow

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zomaotoko.pokedex.R

class ImageAdapter(private val context: Context) : PagerAdapter() {
    private var sprites = ArrayList<String>()
    private var imageArray = ArrayList<ImageView>()

    fun setSprites(sprites: ArrayList<String>) {
        this.sprites = sprites
        imageArray.clear()
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == (`object` as ImageView)
    }

    override fun getCount() = sprites.size

    override fun instantiateItem(container: ViewGroup, position: Int): ImageView {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (position < imageArray.size) return imageArray[position]

        return (inflater.inflate(R.layout.simple_image_view, container, false) as ImageView).apply {
            Glide.with(context).load(sprites[position]).into(this)
            container.addView(this)
            imageArray.add(this)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }
}