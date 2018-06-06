package com.zomaotoko.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import com.zomaotoko.pokedex.pokelist.PokeListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var pokeListFragment: PokeListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        pokeListFragment = (fragmentManager.findFragmentById(R.id.poke_fragment_list) as PokeListFragment)
        configureToolbar()
    }

    private fun configureToolbar() {
        window.statusBarColor = getColor(R.color.colorPrimaryDark)
        setSupportActionBar(toolbar)
        updateToolbar()
    }

    private fun updateToolbar() {
        toolbar.navigationIcon = getDrawable(R.drawable.ic_pokeball)
        invalidateOptionsMenu()
    }

    override fun onStart() {
        super.onStart()
        zoomable.post {
            pokeListFragment.zoomableContainer = zoomable
        }
    }
}
